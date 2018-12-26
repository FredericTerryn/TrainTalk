package com.personal.frederic.TrainTalk.persistence.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.personal.frederic.TrainTalk.persistence.Word

@Dao
interface WoordDao  {

    @Query("SELECT * from word_table")
    fun getAllWords(): LiveData<List<Word>>
}