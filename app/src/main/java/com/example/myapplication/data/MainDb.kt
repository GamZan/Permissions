package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PhotoInfo::class], version = 1)
abstract class MainDb : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    companion object {
        fun getDb(context: Context): MainDb {
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "main_db"
            ).allowMainThreadQueries().build()
        }
    }
}