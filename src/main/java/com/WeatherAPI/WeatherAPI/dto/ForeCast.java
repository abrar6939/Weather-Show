package com.WeatherAPI.WeatherAPI.dto;

import java.util.List;

// Legacy wrapper (kept for backward compatibility). Prefer using `Forecast` and `ForecastDay`.
public class ForeCast {
    private List<ForecastDay> forecastday;

    public List<ForecastDay> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<ForecastDay> forecastday) {
        this.forecastday = forecastday;
    }
}
