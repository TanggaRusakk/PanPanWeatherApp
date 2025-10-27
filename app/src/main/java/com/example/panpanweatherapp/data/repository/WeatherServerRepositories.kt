package com.example.panpanweatherapp.data.repository

import com.example.panpanweatherapp.R
import com.example.panpanweatherapp.data.service.WeatherServerService
import com.example.panpanweatherapp.ui.model.WeatherModel
import java.io.IOException

class WeatherServerRepositories(
    private val service: WeatherServerService
) {
    suspend fun GetWeatherWithCityName(cityName: String): WeatherModel? {
        try {
            val response = service.GetWeatherWithCityName(cityName)

            if (response.isSuccessful) {
                val weather = response.body()

                val dataWeather = WeatherModel(
                    cityName = weather?.name.toString(),
                    time = weather?.dt?.toLong() ?: 0,
                    weather = weather?.weather?.get(0)?.main ?: "",
                    temperature = weather?.main?.temp?.toInt() ?: 0,
                    humidity = weather?.main?.humidity ?: 0,
                    windSpeed = weather?.wind?.speed?.toInt() ?: 0,
                    feelsLike = weather?.main?.feels_like?.toInt() ?: 0,
                    rainFall = weather?.rain?.`1h` ?: 0.0,
                    pressure = weather?.main?.pressure ?: 0,
                    clouds = weather?.clouds?.all ?: 0,
                    sunrise = weather?.sys?.sunrise ?: 0,
                    sunset = weather?.sys?.sunset ?: 0,
                    weatherIcon = weather?.weather?.get(0)?.icon ?: "",
                    weatherImage =
                        if (weather?.weather?.get(0)?.main == "Clouds") R.drawable.blue_and_black_bold_typography_quote_poster
                        else if (weather?.weather?.get(0)?.main == "Rain") R.drawable.blue_and_black_bold_typography_quote_poster_2
                        else if (weather?.weather?.get(0)?.main == "Clear") R.drawable.blue_and_black_bold_typography_quote_poster_3
                        else R.drawable.blue_and_black_bold_typography_quote_poster
                )

                return dataWeather
            } else {
                val weather = response.body()

                val dataWeather = WeatherModel(
                    isError = true,
                    errorCode = "404",
                    errorMessage = "city not found"
                )

                return dataWeather
            }
        } catch (e: IOException) {
            return null
        } catch (e: Exception) {
            return null
        }
    }
}