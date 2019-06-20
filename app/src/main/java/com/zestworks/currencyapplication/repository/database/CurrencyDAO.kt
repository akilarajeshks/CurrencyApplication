package com.zestworks.currencyapplication.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zestworks.currencyapplication.model.Result

@Dao
interface CurrencyDAO {

    @Query("SELECT * from result_table")
    fun getResults(): List<Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addResults(results: List<Result>)

}