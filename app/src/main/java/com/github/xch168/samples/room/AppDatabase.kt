package com.github.xch168.samples.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.xch168.samples.App

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        val db = Room.databaseBuilder(App.appContext, AppDatabase::class.java, "user").build()
    }
}