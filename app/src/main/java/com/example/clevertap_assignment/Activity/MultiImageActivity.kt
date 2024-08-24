package com.example.clevertap_assignment.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clevertap_assignment.Adapter.DogImageListAdapter
import com.example.clevertap_assignment.Data.DataBase.DogDatabase
import com.example.clevertap_assignment.Repository.DogImageRepository
import com.example.clevertap_assignment.ViewModel.MultiImageActivityViewModel
import com.example.clevertap_assignment.ViewModel.MultiImageActivityViewModelFactory
import com.example.clevertap_assignment.databinding.ActivityMultiImageBinding
import com.example.dogimagelibrary.DogImageLibrary
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MultiImageActivity : AppCompatActivity() {

    lateinit var binding: ActivityMultiImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMultiImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val n = intent.getIntExtra("etvSearchValue", 1)
        var dogImageListAdapter = DogImageListAdapter()
        var dogImageLibrary = DogImageLibrary()
        var tableDAO = DogDatabase.getInstance(applicationContext).TableDAO()
        var dogImageRepository = DogImageRepository(tableDAO, dogImageLibrary)

        binding.rvDogImage.layoutManager = LinearLayoutManager(this)
        binding.rvDogImage.adapter = dogImageListAdapter


        var multiImageActivityViewModel =
            ViewModelProvider(this, MultiImageActivityViewModelFactory(dogImageRepository)).get(
                MultiImageActivityViewModel::class.java
            )

        multiImageActivityViewModel.getImageModelList(n)
        lifecycleScope.launch {
            multiImageActivityViewModel.imageModelList.collectLatest {
                dogImageListAdapter.setImageList(it)
            }
        }

        lifecycleScope.launch {
            multiImageActivityViewModel.progressBar.collectLatest {
                if (it) binding.progressbar.visibility = View.VISIBLE
                else binding.progressbar.visibility = View.GONE
            }
        }


    }
}