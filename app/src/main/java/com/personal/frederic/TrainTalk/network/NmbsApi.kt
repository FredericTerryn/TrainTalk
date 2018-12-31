package com.personal.frederic.TrainTalk.network

import com.personal.frederic.TrainTalk.model.Metar
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface NmbsApi {


    @GET("/api/metar/{icao}")
    fun getMetar(@Path("icao") icao: String): Observable<Metar>
}