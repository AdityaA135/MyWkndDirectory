package com.adobe.aem.guides.wknd.core.services;

public interface MyWeatherService {
    /**
     * Interface method to get weather details as a service
     * @param city from request coming as a parameter
     * @return string of data
     */
    String getWeatherReport(String city);
}
