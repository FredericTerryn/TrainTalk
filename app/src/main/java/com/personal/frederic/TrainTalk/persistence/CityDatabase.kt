package com.personal.frederic.TrainTalk.persistence

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.util.Log
import com.personal.frederic.TrainTalk.persistence.daos.CityDao
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch


/**
 * The main acces point for the app's persisted data.
 *
 * The class extends ROOM database.
 * An instance of the database is created by calling Room's Databasebuilder (line 38).
 */
@Database(entities = arrayOf(City::class), version = 1)
abstract class CityDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var INSTANCE: CityDatabase? = null


        fun getDatabase(
            context: Context,
            scope: CoroutineScope //To delete all content and repopulate the database whenever the app is started, you create a RoomDatabase.Callback and override onOpen(). Because you cannot do Room database operations on the UI thread, onOpen() launches a coroutine on the IO Dispatcher.
        ): CityDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CityDatabase::class.java,
                    "Word_database"
                ).addCallback(CityDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }


        /**to delete all content and repopulate the database whenever the app is started,
         *         you create a RoomDatabase.Callback and override onOpen().
         *         Because you cannot do Room database operations on the UI thread, onOpen() launches a coroutine on the IO Dispatcher.
         */
        private class CityDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.cityDao())
                    }
                }
            }

            fun populateDatabase(cityDao: CityDao) {
                cityDao.deleteAll()

                //list of most used Stations in Belgium
                var city = City("Brugge")
                cityDao.insert(city)
                city = City("Oostende")
                cityDao.insert(city)
                city = City("Diksmuide")
                cityDao.insert(city)
                city = City("Ieper")
                cityDao.insert(city)
                city = City("Kortrijk")
                cityDao.insert(city)
                city = City("Waregem")
                cityDao.insert(city)
                city = City("Roeselare")
                cityDao.insert(city)
                city = City("Tielt")
                cityDao.insert(city)
                city = City("Veurne")
                cityDao.insert(city)
                city = City("Leuven")
                cityDao.insert(city)
                city = City("Namur")
                cityDao.insert(city)
                city = City("Brussel-Centraal/Bruxelles-Central")
                cityDao.insert(city)
                city = City("Brussel-Schuman/Bruxelles-Schuman")
                cityDao.insert(city)
                city = City("Brussel-Noord/Bruxelles-Nord")
                cityDao.insert(city)
                city = City("Liège-Guillemins")
                cityDao.insert(city)
                city = City("Charleroi-Ouest")
                cityDao.insert(city)
                city = City("Gent-Sint-Pieters")
                cityDao.insert(city)
                city = City("Antwerpen-Centraal")
                cityDao.insert(city)

            }
        }
    }
}