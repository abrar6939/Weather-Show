package com.WeatherAPI.WeatherAPI.dto;

import java.util.List;

public class WeatherForecast {
    private WeatherResponse weatherResponse;
    private List<DayTemperature> dayTemp;

    public WeatherForecast() {
    }

    public WeatherForecast(WeatherResponse weatherResponse, List<DayTemperature> dayTemp) {
        this.weatherResponse = weatherResponse;
        this.dayTemp = dayTemp;
    }

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public void setWeatherResponse(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }

    public List<DayTemperature> getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(List<DayTemperature> dayTemp) {
        this.dayTemp = dayTemp;
    }
}
