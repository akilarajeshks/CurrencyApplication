package com.zestworks.currencyapplication.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zestworks.currencyapplication.model.CurrencyDatabase
import com.zestworks.currencyapplication.model.CurrencyRepo

class ViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val currencyDao = CurrencyDatabase.getDatabase(context).currencyDao()
        return CurrencyViewModel(CurrencyRepo(currencyDao)) as T
    }
}