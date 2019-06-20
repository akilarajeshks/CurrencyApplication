package com.zestworks.currencyapplication.model

import com.zestworks.currencyapplication.viewModel.RepositoryCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class CurrencyRepo(private val currencyDAO: CurrencyDAO) {


    fun load(repositoryCallback: RepositoryCallback) {
        GlobalScope.launch {
            repositoryCallback.onSuccess(currencyDAO.getResults())
        }
    }
}

