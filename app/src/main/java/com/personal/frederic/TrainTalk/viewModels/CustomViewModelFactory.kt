package com.personal.frederic.TrainTalk.viewModels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

/**
 * Factory for the viewmodels which overrides the create method so a parameter can be passed.
 */

class CustomViewModelFactory(private val test: String):
    ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NmbsViewModel(test) as T
    }
}