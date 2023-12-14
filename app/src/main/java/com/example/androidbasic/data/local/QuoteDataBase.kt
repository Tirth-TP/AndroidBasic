package com.example.androidbasic.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Tirth Patel.
 */

@Database(entities = [Quote::class], version = 1)  //entities = define database Entity classes here
abstract class QuoteDataBase : RoomDatabase() {     //abstract class represent database
    abstract fun quotesDao(): QuoteDao      //We define all dao's here in this class

    companion object {      //Singleton instance
        private var INSTANCE: QuoteDataBase? = null

        //Make sure to pass application context when call this function cuz we need only one room database in hole app
        fun getDatabase(context: Context): QuoteDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        QuoteDataBase::class.java,
                        "quotes_db"   //Name of database that we want to create if not exist
                    )
                        .createFromAsset("quotes.db")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}

/*Using synchronized guards against race conditions where multiple threads might check
INSTANCE == null simultaneously and enter the block concurrently, resulting in the creation
of multiple database instances. The synchronization ensures that the initialization code is
executed atomically by only one thread at a time.
need to use here coz this is multi-threads app */

/*"quotes_database": This is the name you're giving to your database.
If the database with this name doesn't exist, Room will create it.
If it does exist, Room will open it.*/

/*
In Kotlin, a singleton is a design pattern that restricts the instantiation of a class to a single
instance and provides a global point of access to that instance.
Inshore we can directly access the this without creating instance of class
we can access it like  QuoteDataBase.getDatabase() this
*/