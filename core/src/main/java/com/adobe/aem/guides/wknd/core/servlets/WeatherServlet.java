package com.adobe.aem.guides.wknd.core.servlets;

import com.adobe.aem.guides.wknd.core.services.MyWeatherService;
import com.adobe.aem.guides.wknd.core.services.impl.MyWeatherServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.Servlet;
import java.io.IOException;
import java.io.PrintWriter;

@Component(
        service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=Weather Info Servlet",
                "sling.servlet.methods=GET",
                "sling.servlet.paths=/bin/weatherServlet"
        }
)
public class WeatherServlet extends SlingSafeMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(WeatherServlet.class);

    @Reference
    private MyWeatherService weatherService;

    /**
     * Get method to fetch the city name and respond the weather details
     * @param request is the city name
     * @param response is the string with weather details
     * @throws IOException
     */
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String city = request.getParameter(MyWeatherServiceImpl.PARAM);
        response.setContentType(MyWeatherServiceImpl.CONTENT_TYPE);
        log.info("City name: {}",city);
        try (PrintWriter out = response.getWriter()) {
            if (city==null || city.trim().isEmpty()) {
                log.warn("City parameter is missing or empty");
                response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
                out.write(MyWeatherServiceImpl.ERROR_MESSAGE);
                return;
            }
            String result = weatherService.getWeatherReport(city);
            response.setStatus(SlingHttpServletResponse.SC_OK);
            out.write(result);
        }
    }
}
