package com.personal.frederic.TrainTalk.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.personal.frederic.TrainTalk.persistence.daos.CityDao
import okhttp3.internal.Internal.instance

@Database(entities = arrayOf(City::class), version = 1)
abstract class CityDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var INSTANCE: CityDatabase? = null

        fun getDatabase(context: Context): CityDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                context.applicationContext,
                CityDatabase::class.java,
                "Word_database"
            ).build()
                    INSTANCE = instance
                instance
            }
        }
    }
}