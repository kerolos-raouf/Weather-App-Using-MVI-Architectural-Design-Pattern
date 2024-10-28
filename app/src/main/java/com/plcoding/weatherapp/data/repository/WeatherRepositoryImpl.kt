package com.plcoding.weatherapp.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.plcoding.weatherapp.data.mappers.toWeatherInfo

import com.plcoding.weatherapp.data.remote.WeatherApi
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.domain.util.Resource
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            val result = weatherApi.getWeatherData(lat = lat, long = long)
            Resource.Success(
                data = result.toWeatherInfo()
            )
        }catch (e : Exception) {
            e.printStackTrace()
            Log.d("Kerolos", "getWeatherData: ${e.message}")
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}