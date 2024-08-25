package com.example.clevertap_assignment.Repository

import android.util.Log
import com.example.clevertap_assignment.Data.Entity.DogTable
import com.example.clevertap_assignment.Data.DAO.TableDAO
import com.example.dogimagelibrary.DogImageLibrary
import com.example.dogimagelibrary.IOModel.ImageModel

class DogImageRepository(
    private val tableDAO: TableDAO, private val dogImageLibrary: DogImageLibrary
) {
     var currID: Int? = null

    suspend fun getImage(): DogTable {
        var imageModel = dogImageLibrary.getImage()
        var dbImage = tableDAO.insert(DogTable(imageUrl = imageModel.url))
        currID = dbImage.toInt()
        return tableDAO.getImageModelFromID(currID!!)
    }


    suspend fun getNextImage(): DogTable {
        val nextDBImage = currID?.let {
            tableDAO.getNextImage(it)
        }

        return if (nextDBImage != null) {
            currID = nextDBImage.id
            nextDBImage
        } else {
            var imageModel = dogImageLibrary.getImage()
            var dbImage = tableDAO.insert(DogTable(imageUrl = imageModel.url))
            currID = dbImage.toInt()
            tableDAO.getImageModelFromID(currID!!)
        }
    }


    suspend fun getPreviousImage(): DogTable? {
        var preImage = tableDAO.getPrviousImage(currID!!)
        if (preImage != null) currID = preImage.id
        return preImage
    }

    suspend fun isFirstElement(url: String): Boolean {
        if (tableDAO.getFirstImage().imageUrl.equals(url)) return true
        return false

    }

    suspend fun getImageLists(n: Int): List<ImageModel> {
        return dogImageLibrary.getImages(n)
    }

    suspend fun deleteDogImages() {
        tableDAO.deleteImages()
        tableDAO.resetIDtoZero()
    }

}