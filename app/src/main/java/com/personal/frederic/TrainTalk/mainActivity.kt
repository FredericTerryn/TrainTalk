package com.personal.frederic.TrainTalk

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.personal.frederic.TrainTalk.fragments.AirportsFragment
import com.personal.frederic.TrainTalk.fragments.BaseFragment
import com.personal.frederic.TrainTalk.utils.afterTextChanged
import com.personal.frederic.TrainTalk.viewModels.InputViewModel
import kotlinx.android.synthetic.main.activity_main.*

class mainActivity : AppCompatActivity(), AirportsFragment.OnFragmentInteractionListener {

    // private lateinit var inputViewModel: InputViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.addLogAdapter(AndroidLogAdapter())

      //  inputViewModel = ViewModelProviders.of(this).get(InputViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()

        viewpager_main.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                when(p0){
                    BaseFragment.AIRPORTS -> return AirportsFragment.newInstance()
                  //  BaseFragment.RAW -> return RawFragment.newInstance()
                   // BaseFragment.DETAILS -> return DetailsFragment.newInstance()
                   // BaseFragment.OLD -> return OldmetarsFragment.newInstance()
                }
                return AirportsFragment()
            }

            override fun getCount(): Int {
                return 1
            }
        }
    }

    override fun showAirportMetar() {
        Logger.i("Showing the METAR")
    }

}



