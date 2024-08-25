package com.example.dogimagelibrary.API

import android.util.Log
import com.example.dogimagelibrary.IOModel.DogImageOutputModel
import com.example.dogimagelibrary.IOModel.ImageModel
import retrofit2.Response
import java.lang.Exception

class DogImageRepository {
    val dogApiService: DogApiService = RetrofitClient.dogApiService
    var currIndex = -1
    private val dogImagesList = mutableListOf<ImageModel>()

    suspend fun getDogImage(): ImageModel {
        val response = dogApiService.getDogImage()
        if (response.isSuccessful && response != null) {
            val dogImage =
                com.example.dogimagelibrary.IOModel.ImageModel(url = response.body()!!.message)
            dogImagesList.add(dogImage)
            currIndex = dogImagesList.size - 1
            return dogImage
        } else {
            throw Exception("Failed to fetch dog image")
        }

    }

    suspend fun getImages(n: Int): List<ImageModel> {
        val listImageModel = mutableListOf<ImageModel>()
        repeat(n) {
            listImageModel.add(getDogImage())
        }
        return listImageModel
    }

    suspend fun getNextImage(): ImageModel {
        if (currIndex < dogImagesList.size - 1) {
            currIndex++;
            var temp = dogImagesList[currIndex]
            return temp
        }
        var temp = getDogImage()

        return temp
    }

    suspend fun getPreviousimage(): ImageModel? {
     if(currIndex>0)
        {
            currIndex--
            return dogImagesList[currIndex]
        }
        return null

    }


}
