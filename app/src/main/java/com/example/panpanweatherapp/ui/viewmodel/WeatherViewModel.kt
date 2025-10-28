package com.example.panpanweatherapp.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.panpanweatherapp.R
import com.example.panpanweatherapp.data.container.WeatherServerContainer
import com.example.panpanweatherapp.ui.model.WeatherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class WeatherViewModel: ViewModel() {
    private val _weather = MutableStateFlow<WeatherModel?>(null)

    val weather: StateFlow<WeatherModel?> = _weather

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            _weather.value = WeatherServerContainer().WeatherServerRepository.GetWeatherWithCityName(cityName)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(timestamp: Long): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM d", Locale("id"))
            .withZone(ZoneId.systemDefault())
        val formattedDate = formatter.format(Instant.ofEpochSecond(timestamp))
        return formattedDate
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatTime(timestamp: Long): String {
        val formatter = DateTimeFormatter.ofPattern("h:mm a", Locale("en"))
            .withZone(ZoneId.systemDefault())

        val formattedTime = formatter.format(Instant.ofEpochSecond(timestamp))
        return formattedTime
    }
}