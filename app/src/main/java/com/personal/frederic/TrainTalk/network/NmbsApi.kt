package com.personal.frederic.TrainTalk.network


import com.personal.frederic.TrainTalk.model.StationInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface NmbsApi {


    @GET("/liveboard/")
    fun getInfo(@Query("station") icao: String, @Query("format") format: String): Observable<StationInfo>
}