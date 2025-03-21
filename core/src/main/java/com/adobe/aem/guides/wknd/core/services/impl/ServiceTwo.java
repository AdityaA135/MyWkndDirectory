package com.adobe.aem.guides.wknd.core.services.impl;

import com.adobe.aem.guides.wknd.core.configs.ConfigForIP;
import com.adobe.aem.guides.wknd.core.services.IPAddressService;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component(service= IPAddressService.class)
@Designate(ocd = ConfigForIP.class)
public class ServiceTwo implements IPAddressService {
    //private static final String url= "https://europe-west3-devrcc.cloudfunctions.net/whatismyip";

    private String apiUrl;

    @Activate
    @Modified
    protected void activate(ConfigForIP config) {
        this.apiUrl = config.apiUrl();
    }

    @Override
    public String getIP()
    {
        return getData(apiUrl).concat(" coming from second IP");
    }

    public String getData(String url)
    {
        try
        {
            URL urlObj=new URL(url);
            HttpURLConnection conn=(HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader bfr=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resp=new StringBuilder();
            String inputLine;
            while((inputLine = bfr.readLine())!=null)
            {
                resp.append(inputLine);
            }
            bfr.close();
            JSONObject jsonObject = new JSONObject(resp.toString());
            return jsonObject.getString("ip");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
