package com.zestworks.currencyapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zestworks.currencyapplication.model.Result
import com.zestworks.currencyapplication.repository.CurrencyRepo

class CurrencyViewModel(private val currencyRepo: CurrencyRepo) : ViewModel() {

    val currentState = MutableLiveData<State>()

    fun onLoading() {
        currentState.postValue(State.Loading)
        currencyRepo.load(object : RepositoryCallback {
            override fun onSuccess(results: List<Result>) {
                currentState.postValue(State.Success(results))
            }

            override fun onError(reason: String) {
                currentState.postValue(State.Error(reason))
            }
        })

    }

    sealed class State {
        class Success(val results: List<Result>) : State()
        class Error(val reason: String) : State()
        object Loading : State()
    }

}

interface RepositoryCallback {
    fun onSuccess(results: List<Result>)
    fun onError(reason: String)
}