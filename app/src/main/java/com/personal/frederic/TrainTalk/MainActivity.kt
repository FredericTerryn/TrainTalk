package com.personal.frederic.TrainTalk

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.personal.frederic.TrainTalk.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // private lateinit var inputViewModel: CityViewModel
    private val idlingResource = CountingIdlingResource("MAIN_LOADER")
    private val bundle = Bundle()

    fun getIdlingResourceInTest(): CountingIdlingResource{
        return idlingResource
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_item_connectionsList -> {
                changeFragment(HomeFragment(), "home", true, bundle)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_item_favourites -> {
                changeFragment(FavouriteCitiesFragment(), "favourites", true, bundle)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_item_info -> {
                changeFragment(InfoFragment(), "info", true, bundle)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun changeFragment(fragment: Fragment, tag: String, addToStack: Boolean, bundle: Bundle) {
        val fragmentManager = supportFragmentManager
        fragment.arguments = bundle
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (addToStack) {
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar_main))
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
       // Logger.addLogAdapter(AndroidLogAdapter())

        changeFragment(HomeFragment(), "home", true, bundle)

      //  inputViewModel = ViewModelProviders.of(this).get(CityViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        if (supportFragmentManager.backStackEntryCount != 1) {
            when (supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 2).name) {
                "home" -> changeFragment(HomeFragment(), "home", true, bundle)
                "favourites" -> changeFragment(FavouriteCitiesFragment(), "favourites", true, bundle)
                "info" -> changeFragment(InfoFragment(), "info", true, bundle)
            }
        }
    }
       // navigation.setOnNavigationItemSelectedListener(


        /*
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_item_connectionsList -> {
                    viewpager_main.currentItem = BaseFragment.Connections
                    true
                }
                R.id.navigation_item_favourites -> {
                    viewpager_main.currentItem = BaseFragment.FAVOURITES
                    true
                }
                R.id.navigation_item_info -> {
                    viewpager_main.currentItem = BaseFragment.INFO
                    true
                }
                R.id.navigation_item_add ->{
                    viewpager_main.currentItem = BaseFragment.ADD
                    true
                }
                else -> {
                    viewpager_main.currentItem = BaseFragment.FAVOURITES
                    true
                }
            }
        }

        viewpager_main.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                when(p0){
                    BaseFragment.Connections -> return HomeFragment.newInstance()
                    BaseFragment.FAVOURITES -> return FavouriteCitiesFragment.newInstance()
                    BaseFragment.INFO -> return InfoFragment.newInstance()
                    BaseFragment.ADD -> return NewFavouriteCityFragment.newInstance()
                }
                return InfoFragment()
            }

            override fun getCount(): Int {
                return 4
            }
        }
        */
}

    /*
    override fun showAirportMetar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun changeFragment(fragment: Fragment, tag: String, addToStack: Boolean, bundle: Bundle){
        viewpager_main.currentItem = BaseFragment.INFO
        viewpager_main.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                when(p0){
                    BaseFragment.INFO -> return InfoFragment.newInstance()
                }
                return InfoFragment()
            }
            override fun getCount(): Int {
                return 4
            }
        }

        /*val fragmentManager = supportFragmentManager
        fragment.arguments = bundle
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.viewpager_main, fragment)
        fragmentTransaction.commit()*/
    }
    */




