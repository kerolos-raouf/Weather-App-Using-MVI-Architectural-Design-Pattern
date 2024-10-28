package com.plcoding.weatherapp.domain.model

import com.squareup.moshi.Json


data class Hourly(
    @field:Json(name = "pressure_msl")
    val pressure: List<Double>,
    @field:Json(name = "relativehumidity_2m")
    val humidity: List<Double>,
    @field:Json(name = "temperature_2m")
    val temperature: List<Double>,
    val time: List<String>,
    @field:Json(name = "weathercode")
    val weatherCode: List<Int>,
    @field:Json(name = "wind_speed_10m")
    val windSpeed: List<Double>
)