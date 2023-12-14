package com.example.androidbasic.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidbasic.data.repository.QuoteRepository
import com.example.androidbasic.ui.MainViewModel

/**
 * Created by Tirth Patel.
 */
class MainViewModelFactory(private val quoteRepository: QuoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(quoteRepository) as T
    }
}

/*
* If your ViewModel needs parameters during its creation you can use a ViewModelFactory
* to pass these parameters when instantiating the ViewModel.
* */