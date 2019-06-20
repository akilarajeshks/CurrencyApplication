package com.zestworks.currencyapplication.repository.network

import com.zestworks.currencyapplication.model.Currency
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyApi {
    @GET("getcurrencies")
    fun getCurrencies(): Call<Currency>
}