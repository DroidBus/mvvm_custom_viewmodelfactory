package com.example.batterysmart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.batterysmart.databinding.LayoutCountriesAdapterItemBinding
import com.example.batterysmart.listener.CountryClickListiner
import com.example.batterysmart.model.CountryResponse

class CountriesAdapter(private val countryClickListiner: CountryClickListiner,private var countryResponse: ArrayList<CountryResponse>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    var isCountry:Boolean = true

    fun filterList(arrayList: ArrayList<CountryResponse>){
        if(arrayList!=null&&arrayList.isNotEmpty()) {
            countryResponse = arrayList
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutCountriesAdapterItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryResponse = countryResponse.get(position)
        if (countryResponse?.country != null&& isCountry)
            holder.binding.textView.text = countryResponse.country


        holder.binding.root.setOnClickListener {
            countryClickListiner.onCountryClick(countryResponse.country)
        }
    }

    override fun getItemCount(): Int {
        if(isCountry)
        return countryResponse.size
        else
        return countryResponse.get(0).cities!!?.size
    }

    class ViewHolder(val binding: LayoutCountriesAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root)


}