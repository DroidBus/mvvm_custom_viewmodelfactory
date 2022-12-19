package com.example.batterysmart.repository

import com.example.batterysmart.retrofit.RetrofitInstance
import com.example.batterysmart.retrofit.apiinterface.CountryAPI
import retrofit2.Retrofit

class MainRepository(private val countryAPI: CountryAPI) {

    fun getAllCountries()= countryAPI.fetchCountryCityDetails()
}