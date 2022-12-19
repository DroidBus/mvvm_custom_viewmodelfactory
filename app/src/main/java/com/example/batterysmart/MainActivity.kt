package com.example.batterysmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.batterysmart.adapter.CountriesAdapter
import com.example.batterysmart.databinding.ActivityMainBinding
import com.example.batterysmart.model.CountryResponse
import com.example.batterysmart.repository.MainRepository
import com.example.batterysmart.retrofit.RetrofitInstance
import com.example.batterysmart.viewmodel.CountryViewModel
import com.example.batterysmart.viewmodelfactory.MyViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var searchView: SearchView
    lateinit var countryViewModel: CountryViewModel
    lateinit var countries: ArrayList<CountryResponse>
    lateinit var binding: ActivityMainBinding
    var countriesNameList:ArrayList<String?> = ArrayList()
    lateinit var adapter: CountriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = RetrofitInstance.retrofitInstance
        val mainRepository = MainRepository(retrofit)

        val viewModelFactory = MyViewModelFactory(mainRepository)
        countryViewModel =
            ViewModelProvider(this, viewModelFactory).get(CountryViewModel::class.java)

        countryViewModel.getCountryList()

        countryViewModel.countryList.observe(this, Observer {
            setData(it.data)
        })
         binding.countryView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
             override fun onQueryTextChange(newText: String?): Boolean {
                 TODO("Not yet implemented")

             }

             override fun onQueryTextSubmit(query: String?): Boolean {
             if (countriesNameList.contains(query)){

              }else{

             }
                 return false
             }
         })
    }

    fun setData(arrayList: ArrayList<CountryResponse>) {
        countries = arrayList
        getCountry(countries)
        countries.get(0).country?.let { it1 -> Log.i("country is", it1) };

        if (countries != null && countries.isNotEmpty()) {
            val linearLayoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false)
            binding.recyclerViewCountry.layoutManager = linearLayoutManager

            adapter = CountriesAdapter(countries)
            binding.recyclerViewCountry.addItemDecoration(
                DividerItemDecoration(baseContext,
                    linearLayoutManager.orientation)
            )
            binding.recyclerViewCountry.adapter = adapter
        }
    }


    fun getCountry(list: ArrayList<CountryResponse>){
        repeat(list.size-1) { idx ->
            countriesNameList.add(list.get(idx).country)
        }
    }
}