package com.example.batterysmart.listener

import android.icu.text.Transliterator
import java.text.FieldPosition

interface CountryClickListiner {
    fun onCountryClick( country: String?)
}