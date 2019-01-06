package com.personal.frederic.TrainTalk.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.personal.frederic.TrainTalk.R
import com.personal.frederic.TrainTalk.MainActivity
import com.personal.frederic.TrainTalk.viewModels.CityViewModel
import kotlinx.android.synthetic.main.fragment_favourite_cities.view.*

/**
 * Fragment which shows the recyclerview with all the stations.
 *
 * Recieves data from viewModel: CityViewModel and sends data to the Citylistadapter.
 */
class FavouriteCitiesFragment : BaseFragment(), View.OnClickListener {

    private lateinit var cityViewModel: CityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite_cities, container, false)
    }

    /**
     * Instanciate the viewmodel.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cityViewModel = ViewModelProviders.of(this).get(CityViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    /**
     * Observes the data in the viewmodel (this data comes from the repository, is a liveData-list). (A list of cities.) Then instanciates the adapter, with the list.
     * #2 Accepts info from the listener in the adapter (line 58-60 and gives it through, to the method in MainActivity, which changes the fragment.
     * With this info, the right city can be become from the api. (city is placed in bundle)
     */
    private fun showData(){
        cityViewModel.allCities.observe(this, Observer { cities ->

                val adapter = CityListAdapter(cities!!) {
                    val fragment = InfoFragment()
                    val bundle = Bundle()
                    bundle.putString("city", it)
                    (activity as MainActivity).changeFragment(fragment, "info", true, bundle)
                }
                view!!.recyclerviewFavourites.layoutManager = LinearLayoutManager(context)
                view!!.recyclerviewFavourites.adapter = adapter
        })


    }

    override fun onClick(v: View?) {
        Log.d("favourite cities", "clicked on add button ")
    }

    companion object {
       // const val newFavouriteCityFragmentRequestCode = 1 (only used for insert.)
        @JvmStatic
        fun newInstance() = FavouriteCitiesFragment()
    }
}

