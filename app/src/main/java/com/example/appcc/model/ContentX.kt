package com.example.kittheme.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentX(
    val icon: List<String>,
    val isLock: Boolean,
    val previews: List<String>,
    val subtitle: String,
    val title: String,
    val wallpaper: String,
    val wallpaper1: String,
    val wallpaper2: String,

): Parcelable