package com.personal.frederic.TrainTalk.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Entity class: represents a table within the database
 *
 * In our database we store cities. They consist of a string: name
 */
@Entity(tableName = "city_table")
class City (@PrimaryKey @ColumnInfo(name= "city") val city: String)  {
}