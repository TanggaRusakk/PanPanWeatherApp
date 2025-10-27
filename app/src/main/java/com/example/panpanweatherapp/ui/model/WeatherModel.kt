package com.example.panpanweatherapp.ui.model

data class WeatherModel(
    val cityName: String = "",
    val time: Long = 0,
    val weather: String = "",
    val temperature: Int = 0,
    val humidity: Int = 0,
    val windSpeed: Int = 0,
    val feelsLike: Int = 0,
    val rainFall: Double = 0.0,
    val pressure: Int = 0,
    val clouds: Int = 0,
    val sunrise: Int = 0,
    val sunset: Int = 0,
    val weatherIcon: String = "",
    var weatherImage: Int = 0,
    val isError: Boolean = false,
    val errorCode: String = "",
    val errorMessage: String = ""
)
