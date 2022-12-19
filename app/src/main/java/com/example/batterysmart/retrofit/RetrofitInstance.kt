package com.example.batterysmart.retrofit

import com.example.batterysmart.helper.BatterySmartConstants
import com.example.batterysmart.retrofit.apiinterface.CountryAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


//create the instance of retrofit
    val retrofitInstance:CountryAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BatterySmartConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryAPI::class.java)
    }
}