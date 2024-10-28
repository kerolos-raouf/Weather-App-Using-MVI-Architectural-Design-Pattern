package com.plcoding.weatherapp.domain.weather

import java.time.LocalDateTime

data class WeatherData (
    val time : LocalDateTime,
    val temperatureCelsius : Double,
    val pressure : Double,
    val humidity : Double,
    val windSpeed : Double,
    val weatherType : WeatherType
)
