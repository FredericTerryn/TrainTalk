package com.personal.frederic.TrainTalk.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.personal.frederic.TrainTalk.persistence.City
import com.personal.frederic.TrainTalk.persistence.CityDatabase
import com.personal.frederic.TrainTalk.persistence.repositories.CityRepository
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import java.util.logging.Logger
import javax.inject.Inject
import kotlin.coroutines.experimental.CoroutineContext

/**
 * ViewModel which gets the data from the Room database and has this info available for the fragments.
 */
class CityViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)



    private val repository: CityRepository
    val allCities: LiveData<List<City>>


    //Create an init block that gets a reference to the WordDao from the WordRoomDatabase and constructs the WordRepository based on it.
    init {
        val cityDao = CityDatabase.getDatabase(application, scope).cityDao()
        repository = CityRepository(cityDao)
        allCities = repository.AllCities
        println("The cities are $allCities")
    }


    fun insert(city: City) = scope.launch(Dispatchers.IO) {
        repository.insert(city)
    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}

