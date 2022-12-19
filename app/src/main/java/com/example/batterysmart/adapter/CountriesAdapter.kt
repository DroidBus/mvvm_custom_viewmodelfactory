package com.example.batterysmart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.batterysmart.databinding.LayoutCountriesAdapterItemBinding
import com.example.batterysmart.model.CountryResponse

class CountriesAdapter(private val countryResponse: ArrayList<CountryResponse>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutCountriesAdapterItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryResponse = countryResponse.get(position)
        if (countryResponse?.country != null)
            holder.binding.textView.text = countryResponse.country
    }

    override fun getItemCount(): Int {
        return countryResponse.size
    }

    class ViewHolder(val binding: LayoutCountriesAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root)


}