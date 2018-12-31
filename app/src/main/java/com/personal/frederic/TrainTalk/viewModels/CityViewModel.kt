package com.personal.frederic.TrainTalk.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.personal.frederic.TrainTalk.persistence.City
import com.personal.frederic.TrainTalk.persistence.CityDatabase
import com.personal.frederic.TrainTalk.persistence.repositories.CityRepository

/*
Separating your app's UI data from your Activity and Fragment classes lets you better follow the
single responsibility principle: Your activities and fragments are responsible for drawing data to the screen, while your ViewModel can take care of holding and processing all the data needed for the UI.*/

class CityViewModel(application: Application) : AndroidViewModel(application){

    private val repository: CityRepository

    //Add a private LiveData member variable to cache the list of cities.
    //DUS HIER ZITTEN AL JE STEDEN OM TE GEBRUIKEN
    val allCities: LiveData<List<City>>


    //Create an init block that gets a reference to the WordDao from the WordRoomDatabase and constructs the WordRepository based on it.
    init {
        val cityDao = CityDatabase.getDatabase(application).cityDao()
        repository = CityRepository(cityDao)
        allCities = repository.AllCities
    }

   
}
