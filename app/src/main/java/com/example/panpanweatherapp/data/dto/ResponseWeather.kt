package com.example.panpanweatherapp.data.dto

data class ResponseWeather(
    val base: String?,
    val clouds: Clouds?,
    val cod: Int?,
    val coord: Coord?,
    val dt: Int?,
    val id: Int?,
    val main: Main?,
    val message: String?,
    val name: String?,
    val rain: Rain?,
    val snow: Snow?,
    val sys: Sys?,
    val timezone: Int?,
    val visibility: Int?,
    val weather: List<Weather?>?,
    val wind: Wind?
)