package com.github.xch168.samples.lifecycles

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.os.SystemClock
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.github.xch168.samples.R

class LifecycleDemoActivity : AppCompatActivity() {

    private val gpsListener = GpsListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_demo)

        startChronometer()

        subscribeTimer();

        bindGpsListener()

        saveViewModelState();
    }

    private fun startChronometer() {
        val chronometerViewModel by viewModels<ChronometerViewModel>()

        val chronometer: Chronometer = findViewById(R.id.chronometer)
        if (chronometerViewModel.getStartTime() == null) {
            val startTime = SystemClock.elapsedRealtime()
            chronometerViewModel.setStartTime(startTime)
            chronometer.base = startTime
        } else {
            val startTime = chronometerViewModel.getStartTime()
            chronometer.base = startTime ?: 0
        }

        chronometer.start()
    }

    private fun subscribeTimer() {
        val liveDataTimerViewModel by viewModels<LiveDataTimerViewModel>()
        val elapsedTimeObserver: Observer<Long> = Observer {
            findViewById<TextView>(R.id.tv_timer).text = "$it seconds elapsed"
        }
        liveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }

    private fun bindGpsListener() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_LOCATION_PERMISSION_CODE)
        } else {
            BoundLocationManager.bindLocationListener(this, this, gpsListener)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            BoundLocationManager.bindLocationListener(this, this, gpsListener)
        } else {
            Toast.makeText(this, "This sample requires Location access", Toast.LENGTH_SHORT).show()
        }
    }

    inner class GpsListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            findViewById<TextView>(R.id.tv_location).text = "${location?.latitude} , ${location?.longitude}"
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

        override fun onProviderEnabled(provider: String) {
            Toast.makeText(this@LifecycleDemoActivity, "Provider enabled: $provider", Toast.LENGTH_SHORT).show()
        }

        override fun onProviderDisabled(provider: String) {}

    }

    private fun saveViewModelState() {
        val savedStateViewModel by viewModels<SavedStateViewModel>()

        savedStateViewModel.getName().observe(this) {
            findViewById<TextView>(R.id.tv_state).text = "Saved in ViewModel: $it"
        }

        findViewById<Button>(R.id.btn_save).setOnClickListener {
            val newName = findViewById<EditText>(R.id.et_name).text.toString()
            savedStateViewModel.saveNewName(newName)
        }
    }

    companion object {
        const val REQUEST_LOCATION_PERMISSION_CODE = 1

        fun open(context: Context) {
            val intent = Intent(context, LifecycleDemoActivity::class.java)
            context.startActivity(intent)
        }
    }
}