package com.example.appcc.model

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var id: String = "",
    var name: String = "",
    var avarta: String="",
    var favorite: List<ContentX>? = null
) : Parcelable