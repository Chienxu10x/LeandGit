package com.example.appcc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcc.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(

) :BaseViewModel() {
    private val mutableSelectedItem = MutableLiveData<Int>()
    val selectedItem: LiveData<Int> get() = mutableSelectedItem

    fun selectItem(item:Int) {
        mutableSelectedItem.value = item
    }
}