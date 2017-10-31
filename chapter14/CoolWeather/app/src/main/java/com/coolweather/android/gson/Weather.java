package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    /**
     * daily_forecast对应的实体字段是forecastList
     */
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

}
