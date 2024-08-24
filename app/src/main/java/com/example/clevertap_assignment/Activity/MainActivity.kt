package com.example.clevertap_assignment.Activity

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.clevertap_assignment.Data.DataBase.DogDatabase
import com.example.clevertap_assignment.Repository.DogImageRepository
import com.example.clevertap_assignment.ViewModel.MainActivityViewModel
import com.example.clevertap_assignment.ViewModel.MainActivityViewModelFactory
import com.example.clevertap_assignment.Utils.CommonUtils
import com.example.clevertap_assignment.databinding.ActivityMainBinding
import com.example.dogimagelibrary.DogImageLibrary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dogImageLibrary = DogImageLibrary()
        var tableDAO = DogDatabase.getInstance(applicationContext).TableDAO()
        var dogImageRepository = DogImageRepository(tableDAO, dogImageLibrary)
        var commonUtils = CommonUtils()
        mainActivityViewModel =
            ViewModelProvider(this, MainActivityViewModelFactory(dogImageRepository)).get(
                MainActivityViewModel::class.java
            )


        mainActivityViewModel.deleteDogImages()

        lifecycleScope.launch {
            mainActivityViewModel.imageUrl.collectLatest {
                if (it == "EmptyURL") {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            baseContext, "Please check your Intenet Connection!", Toast.LENGTH_SHORT
                        ).show()
                    }
                } else setImageInCardView(it)
            }
        }

        lifecycleScope.launch {
            mainActivityViewModel.isFirst.collectLatest {
                binding.btnPrevious.isEnabled = !it
            }
        }


        binding.btnSubmit.setOnClickListener {
            if (commonUtils.isInternetAvailable(this)) {
                if (binding.etvRequiredImage.text.toString() != "") {
                    var n = binding.etvRequiredImage.text.toString().toInt()
                    if (n <= 0 || n > 10) {
                        commonUtils.shortToast(
                            baseContext, "Search number Should be in the range 1-10"
                        )
                    } else {
                        var intent = Intent(this, MultiImageActivity::class.java)
                        intent.putExtra("etvSearchValue", n)
                        startActivity(intent)
                    }
                }
            } else {
                commonUtils.shortToast(this, "Please check your Internet Connection!")
            }
        }


        binding.btnNext.setOnClickListener {
            mainActivityViewModel.getNextImage()
        }
        binding.btnPrevious.setOnClickListener {
            mainActivityViewModel.getPreviousImage()

        }

        if (commonUtils.isInternetAvailable(this)) {
            lifecycleScope.launch {
                mainActivityViewModel.getImage()

            }
        } else commonUtils.shortToast(this, "Please check your Internet Connection")
    }


    private fun setImageInCardView(url: String) {
        binding.progressbar.visibility=View.VISIBLE
        Glide.with(baseContext).load(url).apply(RequestOptions.centerCropTransform())
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressbar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressbar.visibility = View.GONE
                    return false
                }

            })
            .into(binding.ivDog)

    }


}