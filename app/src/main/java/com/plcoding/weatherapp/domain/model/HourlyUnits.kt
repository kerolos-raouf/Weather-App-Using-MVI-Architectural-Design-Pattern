package com.plcoding.weatherapp.domain.model

import com.squareup.moshi.Json


data class HourlyUnits(
    @field:Json(name = "pressure_msl")
    val pressureUnit: String,
    @field:Json(name = "relativehumidity_2m")
    val humidityUnit: String,
    @field:Json(name = "temperature_2m")
    val temperatureUnit: String,
    val time: String,
    @field:Json(name = "weathercode")
    val weatherCodeUnit: String,
    @field:Json(name = "wind_speed_10m")
    val windSpeedUnit: String
)