package com.github.xch168.samples.lifecycles

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.content.getSystemService
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent


class BoundLocationManager {

    companion object {
        fun bindLocationListener(context: Context, lifecycleOwner: LifecycleOwner, listener: LocationListener) {
            BoundLocationListener(context, lifecycleOwner, listener)
        }
    }

    @SuppressLint("MissingPermission")
    class BoundLocationListener(val context: Context, lifecycleOwner: LifecycleOwner, val listener: LocationListener) : LifecycleObserver {
        private var mLocationManager: LocationManager? = null

        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun addLocationListener() {
            mLocationManager = context.getSystemService()
            mLocationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, listener)
            Log.d("BoundLocationManager", "Listener added")

            val lastLocation = mLocationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            lastLocation?.let { listener.onLocationChanged(it) }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun removeLocationListener() {
            mLocationManager?.removeUpdates(listener)
            mLocationManager = null
            Log.d("BoundLocationManager", "Listener removed")
        }
    }
}