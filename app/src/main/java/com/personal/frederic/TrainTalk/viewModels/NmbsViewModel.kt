package com.personal.frederic.TrainTalk.viewModels

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.orhanobut.logger.Logger
import com.personal.frederic.TrainTalk.fragments.InfoFragment
import com.personal.frederic.TrainTalk.model.StationInfo
import com.personal.frederic.TrainTalk.network.NmbsApi
import com.personal.frederic.TrainTalk.viewModels.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class NmbsViewModel(city: String): BaseViewModel() {

    val rawNmbs = MutableLiveData<StationInfo>()
    private val infoFragment : InfoFragment = InfoFragment()

    @Inject
    lateinit var nmbsapi: NmbsApi

    private var subscription: Disposable

    init {

        println("init method viewModel $city")
        subscription = nmbsapi.getInfo(city, "json")
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

    fun onRetrieveMetarSucces(result: StationInfo){
        println(result)
        rawNmbs.value = result
    }
}