package com.zestworks.currencyapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zestworks.currencyapplication.model.CurrencyRepo
import com.zestworks.currencyapplication.model.Result

class CurrencyViewModel(private val currencyRepo: CurrencyRepo) :ViewModel() {
    fun onLoading() {
            currencyRepo.load(object : RepositoryCallback {
                override fun onSuccess(results: List<Result>) {
                    currentState.postValue(State.Success(results))
                }

                override fun onError(reason: String) {
                    currentState.postValue(State.Error(reason))
                }
            })

    }

    sealed class State{
        class Success(val results: List<Result>):State()
        class Error(val reason:String):State()
        object Loading : State()
    }

    val currentState = MutableLiveData<State>().apply { State.Loading }

}

interface RepositoryCallback{
    fun onSuccess( results : List<Result>)
    fun onError(reason: String)
}