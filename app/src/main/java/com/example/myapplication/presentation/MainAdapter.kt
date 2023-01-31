package com.example.myapplication.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.PhotoInfo
import com.example.myapplication.databinding.PhotoPreviewBinding

class MainAdapter (private val photoList: List<PhotoInfo>):  RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = PhotoPreviewBinding.inflate(LayoutInflater.from(parent.context))
        return MainViewHolder(binding)
    }

    override fun getItemCount(): Int = photoList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = photoList[position]
        with(holder.binding) {
            item.let {
                Glide
                    .with(test.context)
                    .load(it.uri)
                    .centerCrop()
                    .into(test)
            }
        }
    }
}

class MainViewHolder(val binding: PhotoPreviewBinding) : RecyclerView.ViewHolder(binding.root)