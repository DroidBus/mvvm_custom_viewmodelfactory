package com.example.batterysmart

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batterysmart.adapter.CitiesAdapter
import com.example.batterysmart.adapter.CountriesAdapter
import com.example.batterysmart.databinding.ActivityMainBinding
import com.example.batterysmart.dialog.AddToWalletBottomSheet
import com.example.batterysmart.listener.CityClickListener
import com.example.batterysmart.listener.CountryClickListiner
import com.example.batterysmart.model.CountryResponse
import com.example.batterysmart.repository.MainRepository
import com.example.batterysmart.retrofit.RetrofitInstance
import com.example.batterysmart.viewmodel.CountryViewModel
import com.example.batterysmart.viewmodelfactory.MyViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity(), CountryClickListiner, CityClickListener {

    lateinit var countryViewModel: CountryViewModel
    lateinit var countries: ArrayList<CountryResponse>
    lateinit var cities: ArrayList<String>
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: CountriesAdapter
    lateinit var citiesAdapter: CitiesAdapter
    lateinit var listiner: CountryClickListiner
    lateinit var cityClickListener: CityClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listiner = this
        cityClickListener= this
        binding.countryView.setIconifiedByDefault(true);
        binding.countryView.setFocusable(true);
        binding.countryView.setIconified(false);
        binding.countryView.requestFocusFromTouch();
        val retrofit = RetrofitInstance.retrofitInstance
        val mainRepository = MainRepository(retrofit)

        val viewModelFactory = MyViewModelFactory(mainRepository)
        countryViewModel =
            ViewModelProvider(this, viewModelFactory).get(CountryViewModel::class.java)
        binding.progressBar.visibility = ViewGroup.VISIBLE

        GlobalScope.launch {
            countryViewModel.getCountryList()
        }


        countryViewModel.countryList.observe(this, Observer {
            setData(it.data)
        })
        binding.countryView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })


        binding.cityView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                filterCities(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })


    }

    fun setData(arrayList: ArrayList<CountryResponse>) {
        countries = arrayList
        countries.get(0).country?.let { it1 -> Log.i("country is", it1) };
        binding.progressBar.visibility = ViewGroup.GONE
        if (countries != null && countries.isNotEmpty()) {
            val linearLayoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)
            binding.recyclerViewCountry.layoutManager = linearLayoutManager

            adapter = CountriesAdapter(listiner, countries)
            binding.recyclerViewCountry.addItemDecoration(
                DividerItemDecoration(baseContext,
                    linearLayoutManager.orientation)
            )
            binding.recyclerViewCountry.adapter = adapter
        }
    }

    private fun filter(text: String?) {
        val filteredlist = ArrayList<CountryResponse>()
        for (item in countries) {
            if (text?.lowercase(Locale.getDefault())
                    ?.let { item.country?.toLowerCase()?.contains(it) } == true
            ) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            adapter.filterList(filteredlist)
        }
    }

    private fun filterCities(text: String?) {
        val filteredCitylist = ArrayList<String>()
        for (item in cities) {
            if (item.equals(text)) {
                filteredCitylist.add(item)
            }
        }
        if (filteredCitylist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            citiesAdapter.filterList(filteredCitylist)
        }
    }


    override fun onCountryClick(country: String?) {
        Toast.makeText(this, country, Toast.LENGTH_SHORT).show()
        binding.countryView.setQuery(country, false);
        binding.cityView.visibility = ViewGroup.VISIBLE
        binding.recyclerViewCountry.visibility = ViewGroup.VISIBLE
        for(i in 0 until countries.size){
            if(countries.get(i).country.equals(country)){
                countries.get(i).cities?.let { cities.addAll(it) }
            }
        }

        if (cities != null && cities.isNotEmpty()) {
            val linearLayoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)
            binding.recyclerViewCountry.layoutManager = linearLayoutManager

            citiesAdapter = CitiesAdapter(cityClickListener, cities)
            binding.recyclerViewCountry.addItemDecoration(
                DividerItemDecoration(baseContext,
                    linearLayoutManager.orientation)
            )
            binding.recyclerViewCountry.adapter = citiesAdapter
        }
    }

    override fun onCityClick(city: String?) {
    AddToWalletBottomSheet.newInstance().show(supportFragmentManager, "AddToWalletBottomSheet.class")
    }


}