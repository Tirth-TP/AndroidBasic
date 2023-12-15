package com.example.androidbasic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.androidbasic.data.local.Quote
import com.example.androidbasic.data.local.QuoteDataBase
import com.example.androidbasic.data.repository.QuoteRepository
import com.example.androidbasic.databinding.ActivityMainBinding
import com.example.androidbasic.di.MainViewModelFactory
import com.example.androidbasic.ui.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val quotesDao = QuoteDataBase.getDatabase(applicationContext).quotesDao()
        //getDatabase is a singleton that's why we don't need instance of QuoteDataBase
        val quotesRepository = QuoteRepository(quotesDao)

        mainViewModel =
            ViewModelProvider(
                this,
                MainViewModelFactory(quotesRepository)
            )[MainViewModel::class.java]

        mainViewModel.getQuotes().observe(this) {   //Observing data from here
            binding.quotes = it.toString()      //set data with DataBinding
        }
        binding.btnAddQuotes.setOnClickListener {   //Insert quotes on click
            val quote = Quote(0, "This is just a testing.", "Testing")
            mainViewModel.insertQuotes(quote)   //Inserting data from here
        }
    }
}