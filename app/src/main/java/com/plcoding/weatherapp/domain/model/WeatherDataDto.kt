package com.plcoding.weatherapp.domain.model

import com.squareup.moshi.Json


data class WeatherDataDto(
    val elevation: Double,
    val generationtimeMs: Double,
    val hourly: Hourly,
    @field:Json(name = "hourly_units")
    val hourlyUnits: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @field:Json(name = "timezone_abbreviation")
    val timezoneAbbreviation: String,
    val utcOffsetSeconds: Int
)