package com.example.appcc.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentX(
    var icon: List<String>?=null,
    var isLock: Boolean=true,
    var previews: List<String>?=null,
    var subtitle: String="",
    var title: String="",
    var wallpaper: String="",
    ): Parcelable