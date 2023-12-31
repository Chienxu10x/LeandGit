package com.example.appcc.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appcc.model.Content
import com.example.appcc.model.Theme
//import androidx.hilt.lifecycle.ViewModelInject
import com.example.appcc.base.BaseViewModel
import com.example.appcc.data.DataRepository
import com.example.appcc.data.ReadContentRepo
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

    fun loadAllResource(context: Context){
        allTheme.value = readContentRepo.readDataIcon(context)
//        Log.d("TAG", "loadAllResource: "+allTheme.value)
    }


    fun getThemeByFilter(filter: Int){
        currentTheme.value = allTheme.value!!.contents[filter]
    }

    val searchResults by lazy {
        MutableLiveData<List<Content>>()
    }

    fun searchTheme(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val results = readContentRepo.searchThemes(context, query)
            searchResults.postValue(results)
        }
    }


}

