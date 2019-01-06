package com.personal.frederic.TrainTalk.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.personal.frederic.TrainTalk.R
import com.personal.frederic.TrainTalk.persistence.City

/**
 * Adapter for recyclerview of cities.
 *
 * Inflates the itemview of the different cities with stations: recyclerview_item.
 * Has a onclick-listener to change to the infoscreen when needed. (see onBiendViewholder)
 */
class CityListAdapter(private val cities: List<City>, val listener: (String) -> Unit):
    RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {

    override fun getItemCount() = cities.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return CityViewHolder(itemView)
    }

    /**
     * Attaches for each city the textview of one element from recyclerview_item to an element out of the list accepted from the fragment.
     * Contains a listener, when a city is clicked, the listener will send this info to the fragment (see #2 in FavouriteCitiesFragment
     */
    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val current = cities[position]
        holder.cityItemView.text = current.city
        holder.roundtrain.setImageResource(R.drawable.trainround)
        holder.cityItemView.setOnClickListener {
            listener(holder?.cityItemView.text.toString())
        }
    }

    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cityItemView: TextView = itemView.findViewById(R.id.textView)
        val roundtrain: ImageView = itemView.findViewById(R.id.idTrainImage)
    }
}
