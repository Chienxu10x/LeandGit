package com.example.appcc.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.appcc.base.BaseViewModel
import com.example.appcc.data.DataRepository
import com.example.appcc.model.Widget
import com.example.appcc.model.WidgetX
import com.example.kittheme.data.ReadContentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WidgetViewModel @Inject constructor(
    private val readContentRepo : ReadContentRepo,
//    private val dataRepository: DataRepository
)  : BaseViewModel(){
    val allWidget by lazy {
        MutableLiveData<Widget>()
    }

    val currenWidget by lazy {
        MutableLiveData<WidgetX>()
    }

    fun loadAllResource(context: Context){
        allWidget.value = readContentRepo.readDataWidget(context)
    }

    fun getWidgetByFliter(fliter : Int){
        currenWidget.value = allWidget.value!!.widgets[fliter]
    }

//    fun setCurrentWidget(current : Int){
//        dataRepository.setCurrentWidget(current)
//    }
//    fun getCurrentWidget(): Int {
//        return dataRepository.getCurrentWidget()
//    }
//
//    fun setTextColor1(current: String) {
//        dataRepository.setTextColor1(current)
//    }
//
//    fun setTextColor2(current: String) {
//        dataRepository.setTextColor2(current)
//    }
//
//    fun setTextColor3(current: String) {
//        dataRepository.setTextColor3(current)
//    }
//
//    fun setTextColor4(current: String) {
//        dataRepository.setTextColor4(current)
//    }
}