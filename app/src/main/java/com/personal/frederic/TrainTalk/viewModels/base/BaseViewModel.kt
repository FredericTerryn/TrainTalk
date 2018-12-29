package com.personal.frederic.TrainTalk.viewModels.base

import android.arch.lifecycle.ViewModel
import com.personal.frederic.TrainTalk.injection.NetworkModule
import com.personal.frederic.TrainTalk.injection.component.DaggerViewModelInjectorComponent
import com.personal.frederic.TrainTalk.injection.component.ViewModelInjectorComponent
import com.personal.frederic.TrainTalk.viewModels.NmbsViewModel

abstract class BaseViewModel : ViewModel() {
    private val injectorComponent: ViewModelInjectorComponent = DaggerViewModelInjectorComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init{
        inject()
    }

    private fun inject(){
        when(this){
            is NmbsViewModel -> {
                injectorComponent.inject(this)
            }
        }
    }
}
