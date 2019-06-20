package com.zestworks.currencyapplication.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zestworks.currencyapplication.repository.CurrencyRepo
import com.zestworks.currencyapplication.repository.database.CurrencyDatabase
import com.zestworks.currencyapplication.repository.network.CurrencyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val currencyDao = CurrencyDatabase.getDatabase(context).currencyDao()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://bittrex.com/api/v1.1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val currencyApi = retrofit.create(CurrencyApi::class.java)

        return CurrencyViewModel(CurrencyRepo(currencyDao, currencyApi)) as T
    }
}