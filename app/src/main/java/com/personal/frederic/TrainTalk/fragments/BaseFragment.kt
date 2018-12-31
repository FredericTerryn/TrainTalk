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
        val AIRPORTS = 0
        val RAW = 1
        val DETAILS = 2
        val OLD = 3
    }
}
