package com.murerwa.animeapp.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.murerwa.animeapp.R
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

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

fun Bitmap.convertToMultipart(
    context: Context,
    quality: Int = 100,
    fileName: String = "file"
): MultipartBody.Part {
    val imageFile = File(context.externalCacheDir, "temp.jpg")

    val bos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, quality, bos)
    val bitmapdata = bos.toByteArray()

    val imageFile2 = File(context.externalCacheDir, "temp2.bmp")
    imageFile2.createNewFile()

    //write the bytes in file
    val fos = FileOutputStream(imageFile2)
    fos.write(bitmapdata)
    fos.flush()
    fos.close()

    val requestBody = imageFile2.asRequestBody("image/bmp".toMediaTypeOrNull())

    return MultipartBody.Part.createFormData(fileName, imageFile.name, requestBody)
}

fun showToast(context: Context, message: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(context, message, length).show()
}