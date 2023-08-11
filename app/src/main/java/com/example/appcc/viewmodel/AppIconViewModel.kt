package com.example.appcc.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appcc.base.BaseViewModel
import com.example.appcc.model.InstalledApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppIconViewModel @Inject constructor() : BaseViewModel() {
    val listApp by lazy {
        MutableLiveData<List<InstalledApp>>()
    }

    fun loadListApp(context: Context){
        viewModelScope.launch {
            val packageManager = context.packageManager
            val list = packageManager.getInstalledApplications(0)
            val pkgList = mutableListOf<InstalledApp>()
            list.forEach {
                if(packageManager.getLaunchIntentForPackage(it.packageName) != null){
                    pkgList.add(InstalledApp(packageManager.getApplicationIcon(it.packageName)
                    ,it.loadLabel(packageManager).toString(), it.processName,""))
                }
            }
            listApp.value = pkgList
        }
    }
}