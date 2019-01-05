package com.personal.frederic.TrainTalk.fragments


import android.support.v4.app.Fragment



open class BaseFragment : Fragment() {

    open var TAG : String = ""

    companion object {
        val Connections = 0
        val FAVOURITES = 1
        val INFO = 2
        val ADD = 3
    }
}
