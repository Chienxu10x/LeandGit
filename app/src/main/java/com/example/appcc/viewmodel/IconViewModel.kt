package com.example.appcc.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appcc.model.Content
import com.example.appcc.model.Theme
//import androidx.hilt.lifecycle.ViewModelInject
import com.example.appcc.base.BaseViewModel
import com.example.appcc.data.DataRepository
import com.example.kittheme.data.ReadContentRepo
//import androidx.hilt.lifecycle.ViewModelInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IconViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val readContentRepo: ReadContentRepo
): BaseViewModel() {
    fun setLanguage(lang: String) {
        viewModelScope.launch(Dispatchers.IO) {
            readContentRepo.setLanguage(context,lang)
        }
    }
    val allTheme by lazy {
        MutableLiveData<Theme>()
    }

    val currentTheme by lazy {
        MutableLiveData<Content>()
    }

    fun loadAllResource(){
        allTheme.value = readContentRepo.readDataIcon(context)
    }


    fun getThemeByFilter(filter: Int){
        currentTheme.value = allTheme.value!!.contents[filter]
    }

}

