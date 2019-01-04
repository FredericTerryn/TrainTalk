package com.personal.frederic.TrainTalk.model

import android.arch.lifecycle.LiveData
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class StationInfo(
    @field:Json(name = "departures") val departures: Depart
) : Parcelable

@Parcelize
class Depart(
    @field:Json(name = "departure") val singleDeparture: MutableList<Departure>
) : Parcelable


@Parcelize
class Departure(
    val id: Long,
    @field:Json(name = "delay") val delay: String,
    @field:Json(name = "station") val bestemming: String,
    @field:Json(name = "platform") val platform: String,
    @field:Json(name = "time") val time: String

    /*
    @field:Json(name = "Raw-Report") val rawMetar: String, //@field property is from moshi
    @field:Json(name = "Station") val airport: String,
    val dayOfMonth: Int,
    val time: Int,
    @field:Json(name = "Wind-Direction") val windDirection: Int,
    @field:Json(name = "Wind-Speed") val windSpeed: Int,
    @field:Json(name = "Wind-Gust") val gusts: String,
    @field:Json(name = "Visibility") val lineOfSight: Int
    */
) : Parcelable