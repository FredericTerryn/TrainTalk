package com.personal.frederic.TrainTalk.persistence.repositories

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.personal.frederic.TrainTalk.persistence.City
import com.personal.frederic.TrainTalk.persistence.daos.CityDao

/**
 * The repository is used according to the design patterns, so the viewmodel-layer has no direct contact with the persistence layer.
 *
 * Stores the cities, which it got from the database through the daos, in a Livedatalist, which can then be asked for in the viewmodels.
 */
class CityRepository(private val cityDao: CityDao)  {


    val AllCities: LiveData<List<City>> = cityDao.getAllCities()

    /**
    * Adds a wrapper for the insert() method. You must call this on a non-UI thread or the app will crash.
     * Room ensures that you don't do any long-running operations on the main thread, blocking the UI.
     * The @WorkerThread annotation, to mark that this method needs to be called from a non-UI thread.
    */
    @WorkerThread
        suspend fun insert(city:City){
        cityDao.insert(city)
    }
}