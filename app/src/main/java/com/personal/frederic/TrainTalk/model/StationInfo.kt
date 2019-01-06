package com.personal.frederic.TrainTalk.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Model to work with the recieved Json. First you get some general info, where you pick the list "departures"
 */
@Parcelize
class StationInfo(
    @field:Json(name = "departures") val departures: Depart
) : Parcelable

/**
 * In this list, there's another list with all the cities a train departs to.
 */
@Parcelize
class Depart(
    @field:Json(name = "departure") val singleDeparture: MutableList<Departure>
) : Parcelable

/**
 * From each element of this list above, we need to read the information.
 */
@Parcelize
class Departure(
    val id: Long,
    @field:Json(name = "delay") val delay: String,
    @field:Json(name = "station") val bestemming: String,
    @field:Json(name = "platform") val platform: String,
    @field:Json(name = "time") val time: String

) : Parcelable