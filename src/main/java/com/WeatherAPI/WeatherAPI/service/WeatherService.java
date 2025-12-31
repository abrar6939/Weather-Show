package com.WeatherAPI.WeatherAPI.service;

import com.WeatherAPI.WeatherAPI.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.forecast.url}")
    private String apiForecastUrl;
    
    private RestTemplate template=new RestTemplate();
     public String test()
     {
         return "good";
     }

     public WeatherResponse getData(String city)
     {
         String url = apiUrl +"?key="+apiKey+"&q="+city;
         Root response = template.getForObject(url, Root.class);
         WeatherResponse weatherResponse=new WeatherResponse();

         if (response != null && response.getLocation() != null) {
             weatherResponse.setCity(response.getLocation().name);
             weatherResponse.setRegion(response.getLocation().region);
             weatherResponse.setCountry(response.getLocation().country);
         }
         if (response != null && response.getCurrent() != null && response.getCurrent().getCondition() != null) {
             String condition = response.getCurrent().getCondition().getText();
             weatherResponse.setCondition(condition);
             weatherResponse.setTemperature(response.getCurrent().getTemp_c());
         }

         return weatherResponse;
     }

     public WeatherForecast getForecast(String city, int days)
     {
         WeatherResponse weatherResponse = getData(city);
         WeatherForecast response = new WeatherForecast();
         response.setWeatherResponse(weatherResponse);

         List<DayTemperature> dayList = new ArrayList<>();
         String url = apiForecastUrl +"?key="+apiKey+"&q="+city+"&days="+days;
         Root apiResponse = template.getForObject(url, Root.class);
         
         if (apiResponse != null && apiResponse.getForecast() != null) {
             ForeCast forecast = apiResponse.getForecast();
             List<ForecastDay> forecastday = forecast.getForecastday();
             if (forecastday != null) {
                 for(ForecastDay rs: forecastday)
                 {
                     DayTemperature d=new DayTemperature();
                     d.setDate(rs.getDate());
                     d.setMinTemp(rs.getDay().getMintemp_c());
                     d.setAvgTemp(rs.getDay().getAvgtemp_c());
                     d.setMaxTemp(rs.getDay().getMaxtemp_c());
                     dayList.add(d);
                 }
             }
         }

         response.setDayTemp(dayList);
         return response;
     }

     // New API: Compare multiple cities
     public MultiCityComparison compareMultipleCities(List<String> cities)
     {
         List<WeatherResponse> weatherResponses = new ArrayList<>();
         double totalTemp = 0;
         double hottest = Double.NEGATIVE_INFINITY;
         double coldest = Double.POSITIVE_INFINITY;
         String hottestCity = "";
         String coldestCity = "";

         for (String city : cities) {
             WeatherResponse weather = getData(city);
             weatherResponses.add(weather);
             double temp = weather.getTemperature();
             totalTemp += temp;

             if (temp > hottest) {
                 hottest = temp;
                 hottestCity = city;
             }
             if (temp < coldest) {
                 coldest = temp;
                 coldestCity = city;
             }
         }

         double averageTemp = cities.isEmpty() ? 0 : totalTemp / cities.size();
         MultiCityComparison comparison = new MultiCityComparison();
         comparison.setCities(weatherResponses);
         comparison.setHottest(hottestCity + " (" + hottest + "°C)");
         comparison.setColdest(coldestCity + " (" + coldest + "°C)");
         comparison.setAverageTemperature(averageTemp);

         return comparison;
     }

     // New API: Get weather alerts based on conditions
     public WeatherAlert getWeatherAlert(String city)
     {
         WeatherResponse response = getData(city);
         WeatherAlert alert = new WeatherAlert();

         if (response != null) {
             alert.setCity(response.getCity());
             alert.setCondition(response.getCondition());
             alert.setTemperature(response.getTemperature());

             // Generate alerts based on conditions
             if (response.getCondition().toLowerCase().contains("rain")) {
                 alert.setAlert("Rainy weather detected. Carry an umbrella!");
                 alert.setSeverity("MEDIUM");
             } else if (response.getCondition().toLowerCase().contains("storm")) {
                 alert.setAlert("Thunderstorm warning! Stay indoors.");
                 alert.setSeverity("HIGH");
             } else if (response.getCondition().toLowerCase().contains("snow")) {
                 alert.setAlert("Snow warning. Drive carefully.");
                 alert.setSeverity("HIGH");
             } else if (response.getTemperature() < 0) {
                 alert.setAlert("Freezing temperature. Wear warm clothes!");
                 alert.setSeverity("HIGH");
             } else if (response.getTemperature() > 35) {
                 alert.setAlert("Extreme heat warning. Stay hydrated!");
                 alert.setSeverity("HIGH");
             } else {
                 alert.setAlert("Weather conditions are normal.");
                 alert.setSeverity("LOW");
             }
         }

         return alert;
     }

     // New API: Get weather by coordinates
     public WeatherResponse getWeatherByCoordinates(double latitude, double longitude)
     {
         String url = apiUrl + "?key=" + apiKey + "&q=" + latitude + "," + longitude;
         Root response = template.getForObject(url, Root.class);
         WeatherResponse weatherResponse = new WeatherResponse();

         if (response != null && response.getLocation() != null) {
             weatherResponse.setCity(response.getLocation().name);
             weatherResponse.setRegion(response.getLocation().region);
             weatherResponse.setCountry(response.getLocation().country);
         }
         if (response != null && response.getCurrent() != null && response.getCurrent().getCondition() != null) {
             String condition = response.getCurrent().getCondition().getText();
             weatherResponse.setCondition(condition);
             weatherResponse.setTemperature(response.getCurrent().getTemp_c());
         }

         return weatherResponse;
     }

     // New API: Get extended forecast details
     public WeatherForecast getExtendedForecast(String city, int days)
     {
         if (days > 10) {
             days = 10; // API limit
         }
         return getForecast(city, days);
     }
}
