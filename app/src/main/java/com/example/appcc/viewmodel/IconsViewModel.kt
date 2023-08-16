package com.example.appcc.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.appcc.base.BaseViewModel
import com.example.appcc.model.Content
import com.example.appcc.model.Theme
import com.example.appcc.data.ReadContentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class IconsViewModel @Inject constructor(
    private val readContentRepo: ReadContentRepo
): BaseViewModel() {

    val allTheme by lazy {
        MutableLiveData<Theme>()
    }

    val currentTheme by lazy {
        MutableLiveData<Content>()
    }

    fun loadAllResource(context: Context){
        allTheme.value = readContentRepo.readDataIcon(context)
    }


    fun getThemeByFilter(filter: Int){
        currentTheme.value = allTheme.value!!.contents[filter]
    }

}