package com.example.batterysmart.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.batterysmart.model.BaseResponse
import com.example.batterysmart.repository.MainRepository
import com.example.batterysmart.viewmodel.CountryViewModel


class MyViewModelFactory(private val repository: MainRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            CountryViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}