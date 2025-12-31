package com.WeatherAPI.WeatherAPI.dto;

public class DayTemp {
    private String date;
    private double minTemp;
    private double avgTemp;
    private double maxTemp;

    public DayTemp() {
    }

    public DayTemp(String date, double minTemp, double avgTemp, double maxTemp) {
        this.date = date;
        this.minTemp = minTemp;
        this.avgTemp = avgTemp;
        this.maxTemp = maxTemp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }
}
