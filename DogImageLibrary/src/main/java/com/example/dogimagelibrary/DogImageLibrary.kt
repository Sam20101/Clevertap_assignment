package com.example.dogimagelibrary

import android.content.Context
import com.example.dogimagelibrary.API.DogImageRepository
import com.example.dogimagelibrary.IOModel.ImageModel

class DogImageLibrary() {
    private val dogImageRepository = DogImageRepository()
    suspend fun getImage(): ImageModel {
        return dogImageRepository.getDogImage()
    }

    suspend fun getImages(n: Int): List<ImageModel> {
        return dogImageRepository.getImages(n)
    }

    suspend fun getNextImage(): ImageModel {
        return dogImageRepository.getNextImage()
    }

    suspend fun getPreviousImage(): ImageModel? {
        return dogImageRepository.getPreviousimage()
    }

}