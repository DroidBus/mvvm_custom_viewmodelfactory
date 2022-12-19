package com.example.batterysmart.retrofit.apiinterface

import com.example.batterysmart.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryAPI{

    @GET("countries")
    fun fetchCountryCityDetails(): Call<BaseResponse>
}