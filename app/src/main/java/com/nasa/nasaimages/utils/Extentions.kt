package com.nasa.nasaimages.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nasa.nasaimages.R
import com.nasa.nasaimages.data.local.NasaImageEntity
import com.nasa.nasaimages.data.models.NasaImagesResponseModel
import java.util.*

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun ImageView.load(uri: String) {
    Glide.with(this)
        .load(uri)
        .apply(RequestOptions().error(android.R.drawable.gallery_thumb).centerCrop())
        .into(this)
}

fun ImageView.load(uri: Int) {
    Glide.with(this)
        .load(uri)
        .apply(RequestOptions().centerCrop())
        .into(this)
}

fun NasaImagesResponseModel.toEntityList(): MutableList<NasaImageEntity> {
    val list = mutableListOf<NasaImageEntity>()

    this.forEach {
        list.add(
            NasaImageEntity(
                imageTitle = it.title,
                imageExplanation = it.explanation,
                //copyright = it.copyright,
                date = it.date,
                imgUrl = it.url,
                imgHdUrl = it.hdurl,
                mediaType = it.mediaType,
                serviceVersion = it.serviceVersion
            )
        )
    }
    return list
}
