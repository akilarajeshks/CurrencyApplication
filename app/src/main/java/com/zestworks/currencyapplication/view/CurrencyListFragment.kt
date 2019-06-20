package com.zestworks.currencyapplication.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.zestworks.currencyapplication.R
import com.zestworks.currencyapplication.viewModel.CurrencyViewModel
import com.zestworks.currencyapplication.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_currency_list.*


class CurrencyListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_list, container, false)
    }


    override fun onStart() {
        super.onStart()

        val currencyViewModel =
            ViewModelProviders.of(this.activity!!, ViewModelFactory(activity!!.applicationContext!!))[CurrencyViewModel::class.java]

        currencyViewModel.currentState.observe(this, Observer {
            when(it){
                is CurrencyViewModel.State.Success -> {
                    val currency = it.results
                    if(currency_recycler.adapter != null){
                        (currency_recycler.adapter as CurrencyListAdapter).updateResults(currency)
                    }else{
                        currency_recycler.apply {
                            adapter = CurrencyListAdapter(currency)
                            layoutManager = LinearLayoutManager(context)
                        }
                    }
                }
                is CurrencyViewModel.State.Error -> {}
                CurrencyViewModel.State.Loading -> {}
            }
        })
    }
}
