package com.github.xch168.samples.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val mDatabase: AppDatabase = AppDatabase.db
    private val mUserList: LiveData<List<User>> = mDatabase.userDao().getAll();

    fun getUserList(): LiveData<List<User>> {
        return mUserList;
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            mDatabase.userDao().insertAll(user)
        }
    }

}