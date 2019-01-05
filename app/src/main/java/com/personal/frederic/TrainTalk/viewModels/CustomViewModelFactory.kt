package com.personal.frederic.TrainTalk.viewModels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class CustomViewModelFactory(private val test: String):
    ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NmbsViewModel(test) as T
    }
}