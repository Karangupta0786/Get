package com.example.getapikt

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts") // here is end URL in parameter of GET

    fun getData(): Call<myData>
}