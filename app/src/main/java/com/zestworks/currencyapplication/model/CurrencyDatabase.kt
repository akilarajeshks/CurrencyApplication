package com.zestworks.currencyapplication.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Result::class],version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao() : CurrencyDAO

    companion object{
        //@Volatile
        private var INSTANCE: CurrencyDatabase? = null

        fun getDatabase(context: Context): CurrencyDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyDatabase::class.java,
                    "Currency_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}