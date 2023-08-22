package com.example.appcc.data

import android.net.Uri
import com.example.appcc.model.CommentModel
import com.example.appcc.model.ContentX
import com.example.appcc.model.UserModel
import com.example.appcc.utils.UiState

interface TimeLineRepository {
    fun getTimeLine(result: (UiState<MutableList<CommentModel>>) -> Unit)
    fun addTimeLine(commentModel: CommentModel,id:String, result: (UiState<String>) -> Unit)
    fun updateTimeLine(contenx: ContentX, result: (UiState<String>) -> Unit)
    fun getMyimeLine(result: (UiState<MutableList<CommentModel>>) -> Unit)
    fun getFavorite(result: (UiState<MutableList<ContentX>>) -> Unit)

    fun getUserModel(result: (UiState<MutableList<UserModel>>) -> Unit)
    fun addFavorite(contenx: ContentX,titleTheme:String, result: (UiState<String>) -> Unit)

    fun updateProfile(uri: Uri,result: (UiState<String>) -> Unit)
}