package com.example.dogimagelibrary.API

import com.example.dogimagelibrary.IOModel.DogImageOutputModel
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {

    @GET("breeds/image/random")
    suspend fun getDogImage():Response<com.example.dogimagelibrary.IOModel.DogImageOutputModel>
}