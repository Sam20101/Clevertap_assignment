package com.example.clevertap_assignment.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clevertap_assignment.Repository.DogImageRepository
import com.example.dogimagelibrary.IOModel.ImageModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MultiImageActivityViewModel(private val dogImageRepository: DogImageRepository) :ViewModel(){
    private val mImageModelList=MutableStateFlow<List<ImageModel>>(emptyList())
    var imageModelList=mImageModelList.asStateFlow()

    private val mProgressBar= MutableStateFlow<Boolean>(true)
    var progressBar=mProgressBar.asStateFlow()

    fun getImageModelList(n:Int)
    {
        mProgressBar.value=true
        viewModelScope.launch {
            mImageModelList.value=dogImageRepository.getImageLists(n)
            mProgressBar.value=false
        }
    }
}