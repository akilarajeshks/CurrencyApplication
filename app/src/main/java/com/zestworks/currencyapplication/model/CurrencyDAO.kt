package com.zestworks.currencyapplication.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CurrencyDAO {

    @Query("SELECT * from result_table")
    fun getResults() : List<Result>

}