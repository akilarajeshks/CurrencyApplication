package com.zestworks.currencyapplication.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.zestworks.currencyapplication.R
import com.zestworks.currencyapplication.viewModel.CurrencyViewModel
import com.zestworks.currencyapplication.viewModel.ViewModelFactory


class LoaderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loader, container, false)
    }

    override fun onStart() {
        super.onStart()

        val currencyViewModel =
            ViewModelProviders.of(activity!!, ViewModelFactory(activity!!.applicationContext))[CurrencyViewModel::class.java]

        currencyViewModel.currentState.observe(this, Observer {
            when(it){
                is CurrencyViewModel.State.Success -> {
                    findNavController().navigate(R.id.action_loaderFragment_to_currencyListFragment)
                }
                is CurrencyViewModel.State.Error -> {
                    findNavController().navigate(R.id.action_loaderFragment_to_errorFragment)
                }
                CurrencyViewModel.State.Loading -> {}
            }
        })

        currencyViewModel.onLoading()
    }
}
