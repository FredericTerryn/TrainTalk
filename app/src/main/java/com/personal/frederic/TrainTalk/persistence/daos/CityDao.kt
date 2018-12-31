package com.personal.frederic.TrainTalk.persistence.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.personal.frederic.TrainTalk.persistence.City

@Dao
interface CityDao  {

    //in this class you specify sql queries and associate them with method calls.

    @Query("SELECT * from city_table")
    fun getAllCitys(): LiveData<List<City>>

    @Insert
    fun insert(city: City)

    @Delete
    fun delete(city: City)

    @Query("DELETE FROM city_table")
    fun deleteAll(): List<City>
}