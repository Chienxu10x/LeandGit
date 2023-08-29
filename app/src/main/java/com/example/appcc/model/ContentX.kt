package com.example.appcc.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentX(
    var icon: List<String> = emptyList(),
    var isLock: Boolean=true,
    var previews: List<String> = emptyList(),
    var subtitle: String="",
    var title: String="",
    var wallpaper: String="",
    var widget: Int,
    ): Parcelable