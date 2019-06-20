package com.zestworks.currencyapplication.model

import com.zestworks.currencyapplication.viewModel.RepositoryCallback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

open class CurrencyRepo(private val currencyDAO: CurrencyDAO) {

    private val okHttpClient = OkHttpClient.Builder()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://bittrex.com/api/v1.1/public/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())
        .build()

    fun load(repositoryCallback: RepositoryCallback) {
        val currencyData = retrofit.create(GetCurrency::class.java)
        val data = currencyData.getData()

        GlobalScope.launch {
            val response = data.execute()
            if(response.isSuccessful && response.body()!!.success){
                currencyDAO.addResults(response.body()!!.result)
            }else{
                if (currencyDAO.getResults().isEmpty()){
                    repositoryCallback.onError("Data Empty")
                }else{
                    repositoryCallback.onSuccess(currencyDAO.getResults())
                }
            }
            repositoryCallback.onSuccess(currencyDAO.getResults())
        }
    }
}

interface GetCurrency{
    @GET("getcurrencies")
    fun getData(): Call<Currency>
}