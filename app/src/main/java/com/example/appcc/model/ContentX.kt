package com.example.appcc.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentX(
    var icon: List<String>,
    var isLock: Boolean,
    var previews: List<String>,
    var subtitle: String,
    var title: String,
    var wallpaper: String,
    ): Parcelable