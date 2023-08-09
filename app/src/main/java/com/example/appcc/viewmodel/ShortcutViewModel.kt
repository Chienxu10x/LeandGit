package com.example.appcc.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appcc.base.BaseViewModel
import com.example.appcc.extension.createMultipleShortcut
import com.example.appcc.extension.getBitmapFromAsset
import com.example.appcc.model.MyAppIcon
import com.example.appcc.utils.getTotalRam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShortcutViewModel @Inject constructor() : BaseViewModel() {
    val showLoading by lazy {
        MutableLiveData<Boolean>()
    }

    fun installShorcut(context: Context, someList : List<MyAppIcon>){
        val packageManager = context.packageManager
        val totalRam = getTotalRam()
        viewModelScope.launch {
            someList.forEach{
                app ->
                if(app.appIcon != null && app.check){
                    val x = packageManager.getApplicationInfo(app.pkg,0)
                    val bitmap = context.getBitmapFromAsset(app.icon)
                    bitmap?.let {
                        createMultipleShortcut(context, x,it, app.label, false)
                    }
                }
                when {
                    totalRam > 5.0 -> {
                        delay(1000)
                    }
                    totalRam > 4.0 -> {
                        delay(1550)
                    }
                    else -> {
                        delay(2000)
                    }
                }
            }
            withContext(Dispatchers.Main){
                showLoading.value = false
            }
        }
    }
}