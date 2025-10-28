package com.example.panpanweatherapp.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.panpanweatherapp.R
import com.example.panpanweatherapp.data.container.WeatherServerContainer
import com.example.panpanweatherapp.ui.model.WeatherModel
import com.example.panpanweatherapp.ui.viewmodel.WeatherViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InfoView(
    modifier: Modifier = Modifier,
    weather: WeatherModel,
    viewModel: WeatherViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon",
                    tint = Color.White
                )
                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )
                Text(
                    text = "${weather.cityName}",
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
        }
        item {
            Text(
                text = "${viewModel.formatDate(weather.time)}",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .height(4.dp)
            )
            Text(
                text = "Updated as of ${viewModel.formatTime(weather.time)}",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
            Spacer(
                modifier = Modifier
                    .height(128.dp)
            )
        }
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(WeatherServerContainer.BASE_URL_IMAGE + weather.weatherIcon + ".png")
                            .crossfade(true)
                            .build(),
                        contentDescription = "${weather.weather}",
                        modifier = Modifier
                            .size(72.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .height(28.dp)
                    )
                    Text(
                        text = "${weather.weather}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(
                        modifier = Modifier
                            .height(4.dp)
                    )
                    Text(
                        text = "${weather.temperature}°C",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 80.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(
                    modifier = Modifier
                        .width(4.dp)
                )
                Image(
                    painter = painterResource(
                        id = weather.weatherImage
                    ),
                    contentDescription = "${weather.weather}",
                    modifier = Modifier
                        .height(240.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .height(128.dp)
            )
        }
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            color = Color.White.copy(alpha = 0.1f)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                all = 16.dp
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.icon_humidity
                            ),
                            contentDescription = "Humidity Icon",
                            modifier = Modifier
                                .size(32.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )
                        Text(
                            text = "HUMIDITY",
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )
                        Text(
                            text = "${weather.humidity}%",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            color = Color.White.copy(alpha = 0.1f)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                all = 16.dp
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.icon_wind
                            ),
                            contentDescription = "Wind Icon",
                            modifier = Modifier
                                .size(32.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )
                        Text(
                            text = "WIND",
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )
                        Text(
                            text = "${weather.windSpeed}km/h",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            color = Color.White.copy(alpha = 0.1f)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                all = 16.dp
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.icon_feels_like
                            ),
                            contentDescription = "Feels Like Icon",
                            modifier = Modifier
                                .size(32.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )
                        Text(
                            text = "FEELS LIKE",
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )
                        Text(
                            text = "${weather.feelsLike}°",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .height(
                        8.dp
                    )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            color = Color.White.copy(alpha = 0.1f)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                all = 16.dp
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.vector_2
                            ),
                            contentDescription = "Rain Fall Icon",
                            modifier = Modifier
                                .size(32.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )
                        Text(
                            text = "RAIN FALL",
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )
                        Text(
                            text = "${weather.rainFall} mm",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            color = Color.White.copy(alpha = 0.1f)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                all = 16.dp
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.devices
                            ),
                            contentDescription = "Pressure Icon",
                            modifier = Modifier
                                .size(32.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )
                        Text(
                            text = "PRESSURE",
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )
                        Text(
                            text = "${weather.pressure}hPa",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(
                            shape = RoundedCornerShape(16.dp),
                            color = Color.White.copy(alpha = 0.1f)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                all = 16.dp
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.cloud
                            ),
                            contentDescription = "Clouds Icon",
                            modifier = Modifier
                                .size(32.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )
                        Text(
                            text = "CLOUDS",
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                        )
                        Text(
                            text = "${weather.clouds}%",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .height(32.dp)
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.vector
                        ),
                        contentDescription = "Sunrise Icon",
                        modifier = Modifier
                            .size(32.dp)
                    )
                    Spacer(
                        modifier = Modifier
                        .height(4.dp)
                    )
                    Text(
                        text = "SUNRISE",
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )
                    Text(
                        text = "${viewModel.formatTime(weather.sunrise)}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
                Spacer(
                    modifier = Modifier
                        .width(64.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.vector_21png
                        ),
                        contentDescription = "Sunset Icon",
                        modifier = Modifier
                            .size(32.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .height(4.dp)
                    )
                    Text(
                        text = "SUNSET",
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )
                    Text(
                        text = "${viewModel.formatTime(weather.sunset)}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
        }
    }
}

