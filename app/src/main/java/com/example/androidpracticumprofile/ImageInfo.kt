package com.example.androidpracticumprofile

import androidx.annotation.DrawableRes

data class ImageInfo(
    @DrawableRes val imageId: Int,
    @DrawableRes val imageBigId: Int,
    val text: String,
)