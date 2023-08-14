package com.example.appcc.model

import android.os.Parcelable
import androidx.lifecycle.Observer
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentX(
    val icon: List<String>,
    val isLock: Boolean,
    val previews: List<String>,
    val subtitle: String,
    val title: String,
    val wallpaper: String

    ): Parcelable