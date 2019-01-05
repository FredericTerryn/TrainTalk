package com.personal.frederic.TrainTalk.fragments

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.personal.frederic.TrainTalk.R
import com.personal.frederic.TrainTalk.mainActivity
import com.personal.frederic.TrainTalk.persistence.City
import com.personal.frederic.TrainTalk.viewModels.CityViewModel
import kotlinx.android.synthetic.main.fragment_favourite_cities.*
import kotlinx.android.synthetic.main.fragment_favourite_cities.view.*


class FavouriteCitiesFragment : BaseFragment(), View.OnClickListener {

    private var cities: List<City>? = null
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var cityViewModel: CityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_cities, container, false)
        fab2.setOnClickListener{this}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cityViewModel = ViewModelProviders.of(this).get(CityViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    fun onEvent(data: Int){
        //Todo
    }

    private fun showData(){
        cityViewModel.allCities.observe(this, Observer { cities ->

                val adapter = CityListAdapter(cities!!) {
                    val fragment = InfoFragment()
                    val bundle = Bundle()
                    bundle.putString("city", it)
                    (activity as mainActivity).changeFragment(fragment, "info", true, bundle)
                }
                view!!.recyclerviewFavourites.layoutManager = LinearLayoutManager(context)
                view!!.recyclerviewFavourites.adapter = adapter
        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newFavouriteCityFragmentRequestCode && resultCode == Activity.RESULT_OK){
            data?.let {
                val city = City(it.getStringExtra(NewFavouriteCityFragment.EXTRA_REPLY))
                cityViewModel.insert(city)
            }
        } else {
            Toast.makeText(
                context, "could not insert", Toast.LENGTH_LONG).show()

        }
    }

    /*
    override fun onAttach(context: Context) {
        super.onAttach(context)
        TAG = "FavouritesFragment"
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }
    */

    override fun onClick(v: View?) {
        Log.d("favourite cities", "clicked on add button ")
    }
    /*
        fab?.setOnClickListener {
            val intent = Intent(context, NewFavouriteCityFragment::class.java)
            startActivityForResult(intent, newFavouriteCityFragmentRequestCode)
    }
    */

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        fun ShowCities()

    }

    companion object {
        const val newFavouriteCityFragmentRequestCode = 1

        @JvmStatic
        fun newInstance() = FavouriteCitiesFragment()
    }

}

