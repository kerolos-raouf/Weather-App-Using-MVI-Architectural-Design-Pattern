package com.plcoding.weatherapp.data.mappers

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.plcoding.weatherapp.domain.model.Hourly
import com.plcoding.weatherapp.domain.model.WeatherDataDto
import com.plcoding.weatherapp.domain.weather.WeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexWeatherData(
    val index : Int,
    val data : WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
fun Hourly.toWeatherDataMap() : Map<Int, List<WeatherData>>{
    return time.mapIndexed { index, time ->
        val weatherCode = weatherCode[index]
        val temperature = temperature[index]
        val pressure = pressure[index]
        val windSpeed = windSpeed[index]
        val humidity = humidity[index]
        IndexWeatherData(
            index = index,
            data =  WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy{
        it.index / 24
    }.mapValues { indexData->
        indexData.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherInfo() : WeatherInfo {
    val weatherDataMap = hourly.toWeatherDataMap()

    val currentTime = LocalDateTime.now()
    val currentWeather = weatherDataMap[0]?.find {
        val hour = if(currentTime.minute < 30) currentTime.hour else currentTime.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeather
    )
}

