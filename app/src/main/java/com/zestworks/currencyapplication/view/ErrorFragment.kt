package com.zestworks.currencyapplication.view


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zestworks.currencyapplication.R


class ErrorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_error, container, false)
    }


    override fun onStart() {
        super.onStart()

//        val currencyViewModel =
//            ViewModelProviders.of(this, ViewModelFactory(this.context!!))[CurrencyViewModel::class.java]

//        currencyViewModel.currentState.observe(this, Observer {
//            val reason = (it as CurrencyViewModel.State.Error).reason
//            error_text.text=reason
//        })

        view!!.setBackgroundColor(Color.RED)
    }
}
