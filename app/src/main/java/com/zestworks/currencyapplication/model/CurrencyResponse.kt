package com.zestworks.currencyapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


//response data classes
data class Currency(
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("message")
    var message: String = "",
    @SerializedName("result")
    var result: List<Result> = listOf()
)

@Entity(tableName = "result_table")
data class Result(
    @PrimaryKey @SerializedName("Currency") @ColumnInfo(name = "Currency")
    var currency: String = "",
    @SerializedName("CurrencyLong") @ColumnInfo(name = "CurrencyLong")
    var currencyLong: String = "",
    @SerializedName("MinConfirmation") @ColumnInfo(name = "MinConfirmation")
    var minConfirmation: Int = 0,
    @SerializedName("TxFee") @ColumnInfo(name = "TxFee" )
    var txFee: Double = 0.0,
    @SerializedName("IsActive") @ColumnInfo(name = "IsActive")
    var isActive: Boolean = false,
    @SerializedName("IsRestricted") @ColumnInfo(name = "IsRestricted")
    var isRestricted: Boolean = false,
    @SerializedName("CoinType") @ColumnInfo(name = "CoinType")
    var coinType: String = "",
    @SerializedName("BaseAddress") @ColumnInfo(name = "BaseAddress")
    var baseAddress: String? = null,
    @SerializedName("Notice") @ColumnInfo(name = "Notice")
    var notice: String? =  null
)