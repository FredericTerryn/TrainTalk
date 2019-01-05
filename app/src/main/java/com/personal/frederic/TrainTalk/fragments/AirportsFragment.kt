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

    override fun onStart() {
        super.onStart()
        airports = createAirports()
        recyclerview.adapter = SimpleItemRecyclerViewAdapter(airports!!)
        recyclerview.layoutManager = LinearLayoutManager(context)
    }

    private fun createAirports(): List<Airport> {
        val airportList = mutableListOf<Airport>()
        val resources = activity?.applicationContext?.resources
        resources?.let {
            val ids = it.getIntArray(R.array.ids)
            val descriptions = it.getStringArray(R.array.descriptions)
            val locationIndicators = it.getStringArray(R.array.locationIndicator)

            for (i in 0 until ids.size) {
                val airport = Airport(ids[i], locationIndicators[i], descriptions[i])
                airportList.add(airport)
            }
        }
        return airportList
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


    class SimpleItemRecyclerViewAdapter(
        private val airports: List<Airport>
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener = View.OnClickListener {
            Logger.i("Presed an element on the recyclerview")
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_layout, parent, false)
            return ViewHolder(view)
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val airport = airports[position]
            holder.name.text = airport.description
            holder.thumbNail.setImageResource(R.drawable.rounded_button)

            with(holder.thumbNail) {
                tag = airport // Save the airport represented by this view
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = airports.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.textView
            var description: TextView = view.textview_rowlayout_description
            var thumbNail: ImageView = view.imageview_rowlayout_thumbnail
        }
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
            AirportsFragment()
    }
}


