package com.example.androidbasic.data.repository

import androidx.lifecycle.LiveData
import com.example.androidbasic.data.local.Quote
import com.example.androidbasic.data.local.QuoteDao

/**
 * Created by Tirth Patel.
 */

/*
 Repositories communicate with the data sources
 */
class QuoteRepository(private val quoteDao: QuoteDao) {  //For communicating with database it needs instance of DAO
    fun getQuotes(): LiveData<List<Quote>> {
        return quoteDao.getQuotes()
    }

    suspend fun insertQuote(quote: Quote) {
        //When you call this function it will call insertQuote function and pass data that you have send
        quoteDao.insertQuote(quote)
    }
}