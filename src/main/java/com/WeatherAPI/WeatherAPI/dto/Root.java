package com.WeatherAPI.WeatherAPI.dto;



public class Root {
    // this location and current was already existed as we are now adding this 
    //Forecast we have to change some fields
    public Location location;
    public Current current;
    
    public ForeCast forecast;

    public ForeCast getForecast() {
        return forecast;
    }

    public void setForecast(ForeCast forecast) {
        this.forecast = forecast;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}
