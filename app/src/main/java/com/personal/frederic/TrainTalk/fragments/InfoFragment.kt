package com.personal.frederic.TrainTalk.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*

import com.personal.frederic.TrainTalk.R
import com.personal.frederic.TrainTalk.MainActivity
import com.personal.frederic.TrainTalk.model.StationInfo
import com.personal.frederic.TrainTalk.viewModels.CustomViewModelFactory
import com.personal.frederic.TrainTalk.viewModels.NmbsViewModel
import kotlinx.android.synthetic.main.fragment_info.view.*

/**
 * The fragment which shows more detailed info about a station: the next destinations, with time and delays.
 *
 * This fragment can be instanciated without choosing a specific city. Then it's standard set to "Roeselare" (My home city as Author of this code")
 */
class InfoFragment : BaseFragment() {

    private lateinit var nmbsViewModel: NmbsViewModel
    var wantedCity: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    /**
     * Instanciates the viewmodel.
     *
     * If there is a city selected (in FavouriteCitiesfragment), it will be stocked in arguments.
     * This city will be given to the viewmodel so the right api can be collected. Otherwise, it will stock the results for "Roeselare".
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments?.getString("city") == null) {
            wantedCity = "Roeselare"
        } else {
            wantedCity = arguments?.getString("city")
        }
        nmbsViewModel = ViewModelProviders.of(this, CustomViewModelFactory(wantedCity!!)).get(NmbsViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    /**
     * Observes the data in the viewModel and connects it to the adapter.
     *
     * IdlingResources is for the tests. (So the tests don't wait for it).
     */
    private fun showData() {
        (activity as MainActivity).getIdlingResourceInTest()
            .increment() //voor da je data ophaalt zodat test daar nie op wacht
        nmbsViewModel.rawNmbs.observe(this, Observer<StationInfo> { station ->
            val adapter = StationInfoAdapter(station!!.departures.singleDeparture)
            view!!.recyclerviewStationInfo.layoutManager = LinearLayoutManager(context)
            view!!.recyclerviewStationInfo.adapter = adapter
        })
        (activity as MainActivity).getIdlingResourceInTest().decrement()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            InfoFragment()
    }

}
