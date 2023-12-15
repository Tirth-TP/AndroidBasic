package com.example.androidbasic.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Tirth Patel.
 */

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quote")
    fun getQuotes(): LiveData<List<Quote>>

    @Insert
    suspend fun insertQuote(quote: Quote)
}

/*
This all IO function has to be run on background thread for that we make insertQuote suspended function.
And getQuotes will automatically run on background thread cuz it return LiveData.
We normally execute LiveData on main thread*/

