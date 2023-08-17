package com.example.appcc.data

import com.example.appcc.model.ContentX
import com.example.appcc.utils.UiState

interface TimeLineRepository {
    fun getTimeLine(result: (UiState<MutableList<ContentX>>) -> Unit)
    fun addTimeLine(contenx: ContentX, result: (UiState<String>) -> Unit)
    fun updateTimeLine(contenx: ContentX, result: (UiState<String>) -> Unit)
}