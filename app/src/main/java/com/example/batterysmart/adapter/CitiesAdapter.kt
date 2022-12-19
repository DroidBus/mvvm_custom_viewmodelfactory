package com.example.batterysmart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.batterysmart.databinding.LayoutCountriesAdapterItemBinding
import com.example.batterysmart.listener.CityClickListener

class CitiesAdapter(private val cityClickListiner: CityClickListener, private var citiesResponse: ArrayList<String>) :
    RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {


    fun filterList(arrayList: ArrayList<String>) {
        if (arrayList != null && arrayList.isNotEmpty()) {
            citiesResponse = arrayList
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutCountriesAdapterItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = citiesResponse.get(position)
        if (city != null)
            holder.binding.textView.text = city

        holder.binding.root.setOnClickListener {
            cityClickListiner.onCityClick(city)
        }
    }

    override fun getItemCount(): Int {
        return citiesResponse.size

    }

    class ViewHolder(val binding: LayoutCountriesAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root)


}