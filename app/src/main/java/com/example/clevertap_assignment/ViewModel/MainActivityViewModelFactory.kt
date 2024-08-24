package com.example.clevertap_assignment.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clevertap_assignment.Repository.DogImageRepository

class MainActivityViewModelFactory(private val dogImageRepository: DogImageRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java))
            return MainActivityViewModel(dogImageRepository) as T
        else throw IllegalArgumentException("Error in creating MainActivityViewModel")
    }
}