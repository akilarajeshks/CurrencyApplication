package com.zestworks.currencyapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zestworks.currencyapplication.R
import com.zestworks.currencyapplication.model.Result

class CurrencyListAdapter(results:List<Result>) : RecyclerView.Adapter<CurrencyListAdapter.CurrencyViewHolder>(){
    private var resultList : MutableList<Result> = results as MutableList<Result>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.result_item,parent,false))
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val result = resultList[position]
        holder.currencyText.text = result.currency
        holder.currencyLongText.text = result.currencyLong
        holder.txFeeText.text = result.txFee.toString()
    }

    fun updateResults(results: List<Result>){
        this.resultList = results as MutableList<Result>
    }

    class CurrencyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val currencyText: TextView = itemView.findViewById(R.id.currency_text)
        val currencyLongText: TextView = itemView.findViewById(R.id.currencylong_text)
        val txFeeText: TextView = itemView.findViewById(R.id.txfee_text)
    }
}


