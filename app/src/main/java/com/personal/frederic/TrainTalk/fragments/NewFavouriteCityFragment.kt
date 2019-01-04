package com.personal.frederic.TrainTalk.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.personal.frederic.TrainTalk.R
import kotlinx.android.synthetic.main.fragment_new_favourite_city.*

class NewFavouriteCityFragment : BaseFragment() {

    private lateinit var editCityView: EditText
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        button_save.setOnClickListener{
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_city.text)) {
                activity?.setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val city =  edit_city.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, city)
                activity?.setResult(Activity.RESULT_OK,replyIntent)
            }
            activity?.finish()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_favourite_city, container, false)
    }



    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"

    }
}