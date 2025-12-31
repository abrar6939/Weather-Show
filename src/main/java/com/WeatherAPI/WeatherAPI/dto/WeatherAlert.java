package com.WeatherAPI.WeatherAPI.dto;

public class WeatherAlert {
    private String city;
    private String condition;
    private double temperature;
    private String alert;
    private String severity; // LOW, MEDIUM, HIGH

    public WeatherAlert() {
    }

    public WeatherAlert(String city, String condition, double temperature, String alert, String severity) {
        this.city = city;
        this.condition = condition;
        this.temperature = temperature;
        this.alert = alert;
        this.severity = severity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
