package com.github.xch168.samples.lifecycles

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class LiveDataTimerViewModel : ViewModel() {

    private val mElapsedTime: MutableLiveData<Long> = MutableLiveData()

    private var mInitialTime: Long = SystemClock.elapsedRealtime()
    private val timer: Timer = Timer()

    init {
        timer.scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
        }, 1000, 1000)
    }

    fun getElapsedTime(): LiveData<Long> {
        return mElapsedTime
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

}