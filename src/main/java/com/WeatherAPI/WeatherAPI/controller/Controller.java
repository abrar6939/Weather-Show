package com.WeatherAPI.WeatherAPI.controller;

import com.WeatherAPI.WeatherAPI.dto.*;
import com.WeatherAPI.WeatherAPI.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
@CrossOrigin
public class Controller {
    @Autowired
    private WeatherService service;

    @GetMapping("/{city}")
    public String getWeatherData(@PathVariable String city)
    {
        return service.test();
    }

    @GetMapping("/my/{city}")
    public ApiResponse<WeatherResponse> getWeather(@PathVariable String city)
    {
        try {
            WeatherResponse data = service.getData(city);
            return new ApiResponse<>(true, data, "Weather data retrieved successfully");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Error: " + e.getMessage());
        }
    }

    @GetMapping("/forecast")
    public ApiResponse<WeatherForecast> getForecast(@RequestParam String city, @RequestParam int days)
    {
        try {
            WeatherForecast data = service.getForecast(city, days);
            return new ApiResponse<>(true, data, "Forecast retrieved successfully");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Error: " + e.getMessage());
        }
    }

    // NEW API 1: Compare multiple cities
    @PostMapping("/compare")
    public ApiResponse<MultiCityComparison> compareMultipleCities(@RequestBody List<String> cities)
    {
        try {
            MultiCityComparison comparison = service.compareMultipleCities(cities);
            return new ApiResponse<>(true, comparison, "Cities compared successfully");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Error comparing cities: " + e.getMessage());
        }
    }

    // NEW API 2: Get weather alerts
    @GetMapping("/alert/{city}")
    public ApiResponse<WeatherAlert> getWeatherAlert(@PathVariable String city)
    {
        try {
            WeatherAlert alert = service.getWeatherAlert(city);
            return new ApiResponse<>(true, alert, "Weather alert retrieved successfully");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Error: " + e.getMessage());
        }
    }

    // NEW API 3: Get weather by coordinates
    @GetMapping("/coordinates")
    public ApiResponse<WeatherResponse> getWeatherByCoordinates(
            @RequestParam double latitude,
            @RequestParam double longitude)
    {
        try {
            WeatherResponse data = service.getWeatherByCoordinates(latitude, longitude);
            return new ApiResponse<>(true, data, "Weather data retrieved by coordinates");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Error: " + e.getMessage());
        }
    }

    // NEW API 4: Extended forecast
    @GetMapping("/extended-forecast")
    public ApiResponse<WeatherForecast> getExtendedForecast(
            @RequestParam String city,
            @RequestParam(defaultValue = "5") int days)
    {
        try {
            WeatherForecast data = service.getExtendedForecast(city, days);
            return new ApiResponse<>(true, data, "Extended forecast retrieved successfully");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Error: " + e.getMessage());
        }
    }
}
