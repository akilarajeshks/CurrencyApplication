package com.zestworks.currencyapplication.repository

import com.zestworks.currencyapplication.repository.database.CurrencyDAO
import com.zestworks.currencyapplication.repository.network.CurrencyApi
import com.zestworks.currencyapplication.repository.network.Repository
import com.zestworks.currencyapplication.viewModel.RepositoryCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CurrencyRepo(private val currencyDAO: CurrencyDAO, private val currencyApi: CurrencyApi) : Repository {
override fun load(repositoryCallback: RepositoryCallback) {
        GlobalScope.launch {
            val getCurrenciesCall = currencyApi.getCurrencies()
            repositoryCallback.onSuccess(currencyDAO.getResults())
            val response = getCurrenciesCall.execute()
            if (response.isSuccessful && response.body()!!.success) {
                currencyDAO.addResults(response.body()!!.result)
                repositoryCallback.onSuccess(currencyDAO.getResults())
            } else {
                if (currencyDAO.getResults().isEmpty()) {
                    repositoryCallback.onError("DB empty, Network request failed")
                } else {
                    repositoryCallback.onSuccess(currencyDAO.getResults())
                }
            }
        }
    }
}