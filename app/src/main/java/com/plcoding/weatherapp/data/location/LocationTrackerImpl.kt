package com.plcoding.weatherapp.data.location

import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.plcoding.weatherapp.domain.location.LocationTracker
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationTrackerImpl @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {


    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location? {
        if(!isLocationEnabled() || !isGpsEnabled()) {
            return null
        }

        return suspendCancellableCoroutine {cont->
            locationClient.lastLocation.apply {
                if (isComplete){
                    if (isSuccessful){
                        cont.resume(result)
                    }else{
                        cont.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it)
                }
                addOnFailureListener {
                    cont.resume(null)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }


    private fun isLocationEnabled(): Boolean {
        return (ContextCompat.checkSelfPermission(application, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(application, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    }

    private fun isGpsEnabled (): Boolean {
        val locationManager = application.getSystemService(android.content.Context.LOCATION_SERVICE) as android.location.LocationManager
        return (locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER))
    }
}