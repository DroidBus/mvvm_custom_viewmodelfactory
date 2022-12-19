package com.example.batterysmart.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.batterysmart.model.BaseResponse
import com.example.batterysmart.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback

class CountryViewModel(private val mainRepository: MainRepository) : ViewModel() {
    val countryList= MutableLiveData<BaseResponse>()
    val errorMessage = MutableLiveData<String>()

    fun getCountryList(){
        val response= mainRepository.getAllCountries()

        response.enqueue(object : Callback<BaseResponse> {
            override fun onResponse(
                call: Call<BaseResponse>,
                response: retrofit2.Response<BaseResponse>
            ) {
               countryList.value= response.body()
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                errorMessage.value=t.message
            }

        })

    }




}