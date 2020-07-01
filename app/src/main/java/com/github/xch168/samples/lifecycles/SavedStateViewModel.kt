package com.github.xch168.samples.lifecycles

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


private const val NAME_KEY = "name"

class SavedStateViewModel(private val mState: SavedStateHandle) : ViewModel() {

    fun getName(): LiveData<String> {
        return mState.getLiveData(NAME_KEY)
    }

    fun saveNewName(newName: String) {
        mState.set(NAME_KEY, newName)
    }
}