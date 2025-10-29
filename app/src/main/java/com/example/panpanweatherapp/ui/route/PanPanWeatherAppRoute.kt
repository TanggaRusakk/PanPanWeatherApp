package com.example.panpanweatherapp.ui.route

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.panpanweatherapp.ui.view.PanPanWeatherAppView

enum class PanPanWeatherAppView() {
    PanPanWeatherApp
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PanPanWeatherAppRoute() {
    val navController = rememberNavController()

    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = PanPanWeatherAppView.PanPanWeatherApp.name
        ) {
            composable(route = PanPanWeatherAppView.PanPanWeatherApp.name) {
                PanPanWeatherAppView(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = innerPadding
                )
            }
        }

    }
}