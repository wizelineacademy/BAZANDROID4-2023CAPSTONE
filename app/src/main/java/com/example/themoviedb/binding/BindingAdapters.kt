package com.example.themoviedb.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: AppCompatImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}