package com.example.panpanweatherapp.data.container

import com.example.panpanweatherapp.data.repository.WeatherServerRepositories
import com.example.panpanweatherapp.data.service.WeatherServerService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherServerContainer {
    companion object {
        val BASE_URL = "https://api.openweathermap.org/"
        val BASE_URL_IMAGE = "https://openweathermap.org/img/wn/"
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: WeatherServerService by lazy {
        retrofit.create(WeatherServerService::class.java)
    }

    val WeatherServerRepository: WeatherServerRepositories by lazy {
        WeatherServerRepositories(retrofitService)
    }
}