package com.personal.frederic.TrainTalk.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.personal.frederic.TrainTalk.R


open class BaseFragment : Fragment() {

    open var TAG : String = ""

    companion object {
        val Connections = 0
        val FAVOURITES = 1
        val INFO = 2
        val DETAILS = 3
        val OLD = 4
    }
}
