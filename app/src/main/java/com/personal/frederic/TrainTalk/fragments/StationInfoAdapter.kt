package com.personal.frederic.TrainTalk.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import com.personal.frederic.TrainTalk.R
import com.personal.frederic.TrainTalk.model.Departure
import com.personal.frederic.TrainTalk.model.StationInfo
import com.personal.frederic.TrainTalk.persistence.Station
import kotlinx.android.synthetic.main.recyclerview_stationinfo_item.view.*
import java.text.FieldPosition

class StationInfoAdapter(private val stations: MutableList<Departure>):
    RecyclerView.Adapter<StationInfoAdapter.StationInfoViewHolder>()
{
    override fun getItemCount() =  stations.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_stationinfo_item, parent, false)

        return StationInfoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StationInfoViewHolder, position: Int) {
        val current = stations[position]
        holder.stationInfoView.text = ("BESTEMMING:" + current.bestemming)
        holder.stationInfoDelay.text = ("Vertraging (min): " +current.delay)
        holder.thumbnail.setImageResource(R.drawable.station)
    }

    inner class StationInfoViewHolder(view: View): RecyclerView.ViewHolder(view){
        val stationInfoView: TextView = itemView.findViewById(R.id.stationInfoItem_textView)
        val stationInfoDelay: TextView = itemView.findViewById(R.id.stationInfoItem_textView_delay)
        val thumbnail: ImageView = itemView.findViewById(R.id.imageview_infoitem_thumbnail)
    }
}