package com.personal.frederic.TrainTalk.fragments

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.orhanobut.logger.Logger
import com.personal.frederic.TrainTalk.R
import com.personal.frederic.TrainTalk.model.Airport
import kotlinx.android.synthetic.main.fragment_airports.*
import kotlinx.android.synthetic.main.row_layout.view.*
import java.lang.RuntimeException


class AirportsFragment : BaseFragment() {

    private var airports: List<Airport>? = null
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_airports, container, false)
    }



    /*
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        TAG = "AirportsFragment"

        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }
    */

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        fun showAirportMetar()
    }


}


