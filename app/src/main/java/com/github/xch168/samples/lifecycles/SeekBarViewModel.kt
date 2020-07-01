package com.github.xch168.samples.lifecycles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SeekBarViewModel : ViewModel() {

    val seekBarValue = MutableLiveData<Int>()

}