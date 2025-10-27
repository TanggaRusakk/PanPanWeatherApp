package com.example.panpanweatherapp.data.service

import com.example.panpanweatherapp.data.dto.ResponseWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServerService {
    @GET("data/2.5/weather")
    suspend fun GetWeatherWithCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = "0c14c7d5ec069f7b21ae72c74adb7c8e",
        @Query("units") units: String = "metric"
    ): Response<ResponseWeather>
}