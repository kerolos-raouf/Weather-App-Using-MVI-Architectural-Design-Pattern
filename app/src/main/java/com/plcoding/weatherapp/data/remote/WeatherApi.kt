package com.plcoding.weatherapp.data.remote

import com.plcoding.weatherapp.domain.model.WeatherDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {


    @GET("v1/forecast?hourly=temperature_2m,relativehumidity_2m,wind_speed_10m,weathercode,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ) : WeatherDataDto


}