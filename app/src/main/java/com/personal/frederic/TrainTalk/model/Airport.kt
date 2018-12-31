package com.personal.frederic.TrainTalk.model

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
@Parcelize
data class Airport(val id : Int, val locationIndicator : String, val description : String ) : Parcelable