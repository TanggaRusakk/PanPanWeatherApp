package com.example.panpanweatherapp.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.panpanweatherapp.R
import com.example.panpanweatherapp.ui.viewmodel.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PanPanWeatherAppView(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = viewModel(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    var cityName by rememberSaveable {
        mutableStateOf("")
    }

    val weather by viewModel.weather.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.weather___home_2
            ),
            contentDescription = "Background App",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    contentPadding
                )
                .padding(
                    horizontal = 16.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = cityName,
                    onValueChange = {
                        cityName = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(1f),
                    placeholder = {
                        Text(
                            text = "Enter city name...",
                            color = Color.Gray
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White,
                            modifier = Modifier
                                .size(16.dp)
                        )
                    },
                    maxLines = 1,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.Gray,
                        focusedContainerColor = Color.White.copy(alpha = 0.05f),
                        unfocusedContainerColor = Color.White.copy(alpha = 0.05f)
                    )
                )
                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )
                Button(
                    onClick = {
                        viewModel.getWeather(cityName)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(0.4f),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.2f)
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.Gray
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White,
                            modifier = Modifier
                                .size(16.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .width(4.dp)
                        )
                        Text(
                            text = "Search",
                            color = Color.White,
                            maxLines = 1
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )
            if (weather != null) {
                if (weather!!.isError) {
                    ErrorView(
                        errorMessage = "HTTP ${weather!!.errorCode} ${weather!!.errorMessage}"
                    )
                } else {
                    InfoView(
                        weather = weather!!,
                        viewModel = viewModel
                    )
                }
            } else {
                NullView()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun PanPanWeatherAppPreview() {
    PanPanWeatherAppView()
}
