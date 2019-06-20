package com.zestworks.currencyapplication.repository.network

import com.zestworks.currencyapplication.viewModel.RepositoryCallback

interface Repository {
    fun load(repositoryCallback: RepositoryCallback)
}