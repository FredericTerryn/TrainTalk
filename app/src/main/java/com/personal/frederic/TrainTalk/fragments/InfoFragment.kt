package com.personal.frederic.TrainTalk.fragments

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.personal.frederic.TrainTalk.R
import com.personal.frederic.TrainTalk.model.StationInfo
import com.personal.frederic.TrainTalk.persistence.Station
import com.personal.frederic.TrainTalk.viewModels.NmbsViewModel
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_info.view.*


class InfoFragment : BaseFragment() {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var nmbsViewModel: NmbsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nmbsViewModel = ViewModelProviders.of(this).get(NmbsViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    private fun showData() {
        nmbsViewModel.rawNmbs.observe(this, Observer<StationInfo> { station ->
            println("dit zijn de stations" + station)
            val adapter = StationInfoAdapter(station!!.departures.singleDeparture)
            view!!.recyclerviewStationInfo.layoutManager = LinearLayoutManager(context)
            view!!.recyclerviewStationInfo.adapter = adapter
        })
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.detailsClicked()
    }

    /*
    override fun onAttach(context: Context) {
        super.onAttach(context)
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
        // TODO: Update argument type and name
        fun detailsClicked()
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment AirportsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            InfoFragment()
    }

}
