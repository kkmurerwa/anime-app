package com.murerwa.animeapp.core.utils

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.murerwa.animeapp.R

fun ImageView.loadImage(
    imageUrl: String,
    customDrawable: Int = R.drawable.ic_shows
) {
    // Create spinner drawable for the glide placeholder
    val progressDrawable = CircularProgressDrawable(this.context)
    progressDrawable.strokeWidth = 5f
    progressDrawable.centerRadius = 30f
    progressDrawable.start()

    // Load images with glide
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(progressDrawable)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .error(customDrawable)
        .fallback(customDrawable)
        .into(this)
}