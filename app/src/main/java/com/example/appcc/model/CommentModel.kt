package com.example.appcc.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentModel (
    var id:String="",
    var idUser:String="",
    var avatar:String="",
    var userName:String="",
    var comnet:String="",
    var contentX: ContentX?=null
):Parcelable