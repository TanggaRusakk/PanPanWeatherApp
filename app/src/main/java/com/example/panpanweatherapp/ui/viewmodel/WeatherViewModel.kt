package com.example.panpanweatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.panpanweatherapp.R
import com.example.panpanweatherapp.data.container.WeatherServerContainer
import com.example.panpanweatherapp.ui.model.WeatherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {
    private val _weather = MutableStateFlow<WeatherModel?>(null)

    val weather: StateFlow<WeatherModel?> = _weather

    fun getWeather(cityName: String) {
        viewModelScope.launch {
            _weather.value = WeatherServerContainer().WeatherServerRepository.GetWeatherWithCityName(cityName)
        }

        _weather.value?.weatherImage =
            if (_weather.value!!.weather == "Clouds") {
                R.drawable.blue_and_black_bold_typography_quote_poster
            } else if (_weather.value!!.weather == "Rain") {
                R.drawable.blue_and_black_bold_typography_quote_poster_2
            } else if (_weather.value!!.weather == "Clear") {
                R.drawable.blue_and_black_bold_typography_quote_poster_3
            } else {
                R.drawable.blue_and_black_bold_typography_quote_poster
            }
    }
}