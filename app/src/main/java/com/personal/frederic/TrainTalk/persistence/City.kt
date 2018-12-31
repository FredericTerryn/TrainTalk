package com.personal.frederic.TrainTalk.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "city_table")
class City (@PrimaryKey @ColumnInfo(name= "city") val city: String)  {
}