package com.andresrivas.bazpeliculasyseries.utilities

import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.andresrivas.bazpeliculasyseries.R
import com.squareup.picasso.Picasso

fun ImageView.fromUrl(url: String) {
    AppCompatResources.getDrawable(context, R.drawable.image_not_available)?.let {
        Picasso.get().load(url).error(it).into(this)
    }
}