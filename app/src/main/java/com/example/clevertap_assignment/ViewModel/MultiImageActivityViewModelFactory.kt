package com.example.clevertap_assignment.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clevertap_assignment.Repository.DogImageRepository

class MultiImageActivityViewModelFactory(private val dogImageRepository: DogImageRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MultiImageActivityViewModel::class.java))
            return MultiImageActivityViewModel(dogImageRepository) as T
        else throw IllegalArgumentException("Error in making multiImageactivity ViewModel")
    }
}