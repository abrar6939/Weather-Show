package com.WeatherAPI.WeatherAPI.dto;

import java.util.List;

public class MultiCityComparison {
    private List<WeatherResponse> cities;
    private String hottest;
    private String coldest;
    private double averageTemperature;

    public MultiCityComparison() {
    }

    public MultiCityComparison(List<WeatherResponse> cities, String hottest, String coldest, double averageTemperature) {
        this.cities = cities;
        this.hottest = hottest;
        this.coldest = coldest;
        this.averageTemperature = averageTemperature;
    }

    public List<WeatherResponse> getCities() {
        return cities;
    }

    public void setCities(List<WeatherResponse> cities) {
        this.cities = cities;
    }

    public String getHottest() {
        return hottest;
    }

    public void setHottest(String hottest) {
        this.hottest = hottest;
    }

    public String getColdest() {
        return coldest;
    }

    public void setColdest(String coldest) {
        this.coldest = coldest;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }
}
