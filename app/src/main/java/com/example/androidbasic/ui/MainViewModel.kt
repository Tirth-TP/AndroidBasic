package com.example.androidbasic.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidbasic.data.local.Quote
import com.example.androidbasic.data.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Tirth Patel.
 */
class MainViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {
    fun getQuotes(): LiveData<List<Quote>> {
        //Data that we revive from repo, this method will pass to the view (UI)
        return quoteRepository.getQuotes()
    }

    fun insertQuotes(quote: Quote) {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.insertQuote(quote)
        }
    }
}


/*
* View related data maintain in view model
* View model communicate with repository that's why we pass repository in constructor
* */