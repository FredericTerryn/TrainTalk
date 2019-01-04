package com.personal.frederic.TrainTalk.fragments

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.personal.frederic.TrainTalk.R
import com.personal.frederic.TrainTalk.persistence.City


class CityListAdapter(private val cities: List<City>):
    RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {


    override fun getItemCount() = cities.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val current = cities[position]
        holder.cityItemView.text = current.city
    }




    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityItemView: TextView = itemView.findViewById(R.id.textView)
    }
}