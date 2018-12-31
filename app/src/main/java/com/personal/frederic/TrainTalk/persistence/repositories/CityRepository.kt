package com.personal.frederic.TrainTalk.persistence.repositories

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.personal.frederic.TrainTalk.persistence.City
import com.personal.frederic.TrainTalk.persistence.daos.CityDao

class CityRepository(private val cityDao: CityDao)  {

    /*Add the list of words as a public property and initialize it. Room executes all queries on a separate thread. Observed LiveData will notify the observer when the data has changed.*/
    val AllCities: LiveData<List<City>> = cityDao.getAllCitys()

    /*
    Add a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash. Room ensures that you don't do any long-running operations on the main thread, blocking the UI. Add the @WorkerThread annotation, to mark that this method needs to be called from a non-UI thread. Add the suspend modifier to tell the compiler that this needs to be called from a coroutine or another suspending function.
    */
    @WorkerThread
        suspend fun insert(city:City){
        cityDao.insert(city)
    }
}