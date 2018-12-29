package com.personal.frederic.TrainTalk.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.personal.frederic.TrainTalk.network.NmbsApi
import com.personal.frederic.TrainTalk.viewModels.base.BaseViewModel
import javax.inject.Inject


class NmbsViewModel: BaseViewModel() {

    private val rawNmbs = MutableLiveData<String>()

    @Inject
    lateinit var nmbsapi: NmbsApi
}