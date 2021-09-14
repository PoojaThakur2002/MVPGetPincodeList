package com.example.weatherapp.Retrofit

import com.example.androidtask.Models.Pincode
import retrofit2.Call
import retrofit2.http.*

 interface ApiInterface {

    @GET(".")
    fun getData(): Call<List<Pincode>>

}