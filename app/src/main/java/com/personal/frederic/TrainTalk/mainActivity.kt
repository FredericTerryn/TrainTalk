package com.personal.frederic.TrainTalk

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.personal.frederic.TrainTalk.fragments.AirportsFragment
import com.personal.frederic.TrainTalk.fragments.BaseFragment
import com.personal.frederic.TrainTalk.fragments.FavouriteCitiesFragment
import kotlinx.android.synthetic.main.activity_main.*

class mainActivity : AppCompatActivity(), AirportsFragment.OnFragmentInteractionListener {

    // private lateinit var inputViewModel: CityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())

      //  inputViewModel = ViewModelProviders.of(this).get(CityViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        navigation.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.navigation_item_connectionsList -> {
                    viewpager_main.currentItem = BaseFragment.Connections
                }
                R.id.navigation_item_favourites -> {
                    viewpager_main.currentItem = BaseFragment.FAVOURITES
                }
            }
        }

        viewpager_main.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                when(p0){
                    BaseFragment.Connections -> return AirportsFragment.newInstance()
                    BaseFragment.FAVOURITES -> return FavouriteCitiesFragment.newInstance()
                   // BaseFragment.DETAILS -> return DetailsFragment.newInstance()
                   // BaseFragment.OLD -> return OldmetarsFragment.newInstance()
                }
                return AirportsFragment()
            }

            override fun getCount(): Int {
                return 2
            }
        }
    }

    override fun showAirportMetar() {
        Logger.i("Showing the METAR")
    }

}



