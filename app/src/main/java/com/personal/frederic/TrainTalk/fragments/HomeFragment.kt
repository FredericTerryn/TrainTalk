package com.personal.frederic.TrainTalk.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.personal.frederic.TrainTalk.R


class HomeFragment : BaseFragment() {

    /**
     * Inflates home layout.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_airports, container, false)
    }

}

