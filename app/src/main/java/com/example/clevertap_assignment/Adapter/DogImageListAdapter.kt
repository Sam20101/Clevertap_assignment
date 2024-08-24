package com.example.clevertap_assignment.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.clevertap_assignment.R
import com.example.dogimagelibrary.IOModel.ImageModel

class DogImageListAdapter() : RecyclerView.Adapter<DogImageListAdapter.ViewHolder>() {

    private lateinit var mImageList: List<ImageModel>
    fun setImageList(imageList: List<ImageModel>) {
        mImageList = imageList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivDog = itemView.findViewById<ImageView>(R.id.ivDog)
        fun setImageModel(imageModel: ImageModel) {
            Glide.with(itemView.context).load(imageModel.url)
                .apply(RequestOptions.centerCropTransform()).into(ivDog)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_dog_image, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mImageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var imageModel = mImageList[position]
        holder.setImageModel(imageModel)

    }
}