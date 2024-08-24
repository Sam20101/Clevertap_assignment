package com.example.dogimagelibrary.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    val dogApiService: DogApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(DogApiService::class.java)
    }
}