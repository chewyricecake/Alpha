package com.example.alpha.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alpha.Model.Parking
import com.example.alpha.databinding.ActivityMainBinding
import com.example.alpha.databinding.ParkingElementBinding

class ParkingAdapter(val list: List<Parking>): RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder>() {

    val TAG = "ParkingAdapter"

    inner class ParkingViewHolder(val binding: ParkingElementBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        return ParkingViewHolder(
            ParkingElementBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        Log.e(TAG, "id: " + list[position].location.toString())

        holder.binding.apply {
            var parking = list[position]
            title.text = parking.title
            available.text = parking.available.toString()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}