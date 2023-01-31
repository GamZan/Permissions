package com.example.myapplication.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo")
    fun getAll(): List<PhotoInfo>

    @Insert
    fun insert(photoInfo: PhotoInfo)

    @Delete
    fun delete(photoInfo: PhotoInfo)

    @Update
    fun update(photoInfo: PhotoInfo)
}