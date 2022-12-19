package com.example.batterysmart.model

data class CountryResponse(val iso2:String,
                           val iso3:String,
                           val country:String?,
                           val cities:ArrayList<String>?
                           )
