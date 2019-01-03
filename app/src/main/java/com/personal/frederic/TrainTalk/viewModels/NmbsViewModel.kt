package com.personal.frederic.TrainTalk.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.orhanobut.logger.Logger
import com.personal.frederic.TrainTalk.model.Metar
import com.personal.frederic.TrainTalk.network.NmbsApi
import com.personal.frederic.TrainTalk.viewModels.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class NmbsViewModel: BaseViewModel() {

    private val rawNmbs = MutableLiveData<String>()

    @Inject
    lateinit var nmbsapi: NmbsApi

    private var subscription: Disposable

    init {
        subscription = nmbsapi.getMetar("EBOS")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result -> onRetrieveMetarSucces(result)},
                {error -> onRetrieveMetarError(error)}
            )
    }

    fun onRetrieveMetarError(error: Throwable){
        Logger.e(error.message!!)
    }

    fun onRetrieveMetarSucces(result: Metar){
        rawNmbs.value = result.rawMetar
    }
}