package com.example.clevertap_assignment.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clevertap_assignment.Repository.DogImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val dogImageRepository: DogImageRepository) : ViewModel() {

    private val mImageUrl = MutableStateFlow<String>("")
    var imageUrl = mImageUrl.asStateFlow()

    private val mIsFirst = MutableStateFlow<Boolean>(true)
    var isFirst = mIsFirst.asStateFlow()


    fun getImage() {
        viewModelScope.launch {
            mImageUrl.value = dogImageRepository.getImage().imageUrl
        }
    }

    fun getNextImage() {
        viewModelScope.launch {
            try {
                var nextImg = dogImageRepository.getNextImage().imageUrl
                mImageUrl.value = nextImg
                mIsFirst.value = dogImageRepository.isFirstElement(nextImg)
            } catch (e: Exception) {
                mImageUrl.value = "EmptyURL"
            }

        }
    }

    fun getPreviousImage() {
        viewModelScope.launch {
            val preImage = dogImageRepository.getPreviousImage()
            if (preImage != null) {
                mImageUrl.value = preImage?.imageUrl.toString()
                if (dogImageRepository.isFirstElement(preImage.imageUrl)) mIsFirst.value = true
            }
        }
    }

    fun deleteDogImages() {
        viewModelScope.launch {
            dogImageRepository.deleteDogImages()
        }
    }


}