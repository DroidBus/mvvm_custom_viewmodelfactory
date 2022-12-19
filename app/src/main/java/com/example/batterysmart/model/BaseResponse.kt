package com.example.batterysmart.model


data class BaseResponse(val error:String,
                        val msg:String,
                        val data:ArrayList<CountryResponse>
                        )
