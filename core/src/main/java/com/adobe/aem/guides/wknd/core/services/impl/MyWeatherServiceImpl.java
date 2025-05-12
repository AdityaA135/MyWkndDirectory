package com.adobe.aem.guides.wknd.core.services.impl;
import com.adobe.aem.guides.wknd.core.configs.WeatherConfig;
import com.adobe.aem.guides.wknd.core.services.MyWeatherService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Component(service = MyWeatherService.class)
@Designate(ocd = WeatherConfig.class, factory = true)
public class MyWeatherServiceImpl implements MyWeatherService {

    private static final Logger log = LoggerFactory.getLogger(MyWeatherServiceImpl.class);

    private static final String END_URL = "&units=metric";
    public static final String METHOD = "GET";
    public static final String PARAM = "city";
    public static final String CONTENT_TYPE = "application/json";
    public static final String ERROR_MESSAGE = "{\"error\": \"Wrong City, please check the spelling\"}";
    public static final String FETCH_ERROR = "{\"error\": \"Failed to fetch weather data from external API\"}";
    public static final String INTERNAL_SERVER_ERROR = "{\"error\": \"Internal server error occurred while fetching data\"}";
    public static final String EXCEPTION_MESSAGE = "Unexpected exception occurred";
    public static final String INPUT_OUTPUT_EXCEPTION = "IOException occurred while calling external API";
    public static final String TIMESTAMP_FORMAT = "hh:mm:ss a z";
    private static final String apiMidUrl="&appid=";
    private String apiUrl;
    private String apiKey;


    /**
     * Activation function for configuration
     * @param config object with api start-url, mid-url and key
     */
    @Activate
    @Modified
    protected void activate(WeatherConfig config) {
        this.apiUrl = config.apiUrl();
        this.apiKey = config.apiKey();
    }

    /**
     * Weather report method which generates string of requested weather data
     * @param city from request coming as a parameter
     * @return String of weather details: Temperature, Wind speed, Sky description and timestamp
     */
    @Override
    public String getWeatherReport(String city) {
        HttpURLConnection con = null;
        BufferedReader in = null;

        try {
            city = city.toLowerCase().trim().replaceAll("\\s+", " ");
            city = URLEncoder.encode(city, "UTF-8").replace("+", "%20");

            String requestUrl = apiUrl + "?q=" + city + apiMidUrl + apiKey + END_URL;
            log.info("Requesting Weather API: {}", requestUrl);

            con = (HttpURLConnection) new URL(requestUrl).openConnection();
            con.setRequestMethod(METHOD);

            int status = con.getResponseCode();
            if (status == 404) {
                log.warn("City not found: {}", city);
                return ERROR_MESSAGE;
            }
            if (status != 200) {
                log.error("External API returned non-OK status: {}", status);
                return FETCH_ERROR;
            }
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder jsonResponse = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                jsonResponse.append(line);
            }

            JsonObject weatherData = JsonParser.parseString(jsonResponse.toString()).getAsJsonObject();
            double temp = weatherData.getAsJsonObject("main").get("temp").getAsDouble();
            double windSpeed = weatherData.getAsJsonObject("wind").get("speed").getAsDouble();
            String condition = weatherData.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();

            ZonedDateTime now = ZonedDateTime.now(TimeZone.getTimeZone("Asia/Kolkata").toZoneId());
            String timestamp = now.format(DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT));

            JsonObject json = new JsonObject();
            json.addProperty("Temperature", temp + " °C");
            json.addProperty("Wind Speed", windSpeed + " m/s");
            json.addProperty("Weather Condition", condition);
            json.addProperty("Timestamp", timestamp);
            return json.toString();

        } catch (java.io.UnsupportedEncodingException e) {
            log.error("Encoding error for city: {}", city, e);
            return "{\"error\": \"Invalid city name encoding\"}";
        } catch (java.io.IOException e) {
            log.error(INPUT_OUTPUT_EXCEPTION, e);
            return FETCH_ERROR;
        } catch (JSONException e) {
            log.error("Error parsing weather API response", e);
            return "{\"error\": \"Malformed data received from weather service\"}";
        } catch (Exception e) {
            log.error(EXCEPTION_MESSAGE, e);
            return INTERNAL_SERVER_ERROR;
        } finally {
            try {
                in.close();
                con.disconnect();
            } catch (Exception e) {
                log.warn("Error closing resources", e);
            }
        }
    }

}
