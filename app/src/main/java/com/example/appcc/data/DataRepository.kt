package com.example.appcc.data

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.appcc.extension.dataStore
import com.example.appcc.viewmodel.IconViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    @ApplicationContext context: Context,
    private val settingPref : SharedPreferences
) {

    val currentWidget = "currentWidget"
    fun getCurrentWidget(): Int{
        return settingPref.getInt(currentWidget,0)
    }

    fun setCurrentWidget(current: Int){
        settingPref.edit().putInt(currentWidget,current).apply()
    }

    val color1 = "textColor1"
    fun getTextColor1(): String? {
        return settingPref.getString(color1, "#000000")
    }

    fun setTextColor1(current: String) {
        settingPref.edit().putString(color1, current).apply()
    }

    val color2 = "textColor2"
    fun getTextColor2(): String? {
        return settingPref.getString(color2, "#000000")
    }

    fun setTextColor2(current: String) {
        settingPref.edit().putString(color2, current).apply()
    }

    val color3 = "textColor3"
    fun getTextColor3(): String? {
        return settingPref.getString(color3, "#000000")
    }

    fun setTextColor3(current: String) {
        settingPref.edit().putString(color3, current).apply()
    }

    val color4 = "textColor4"
    fun getTextColor4(): String? {
        return settingPref.getString(color4, "#000000")
    }

    fun setTextColor4(current: String) {
        settingPref.edit().putString(color4, current).apply()
    }

    val currentWidgetPosition = "currentWidgetInt1"
    fun getCurrentWidgetPosition(): Int{
        return settingPref.getInt(currentWidgetPosition,0)
    }

    fun setCurrentWidgetPosition(current: Int){
        settingPref.edit().putInt(currentWidgetPosition,current).apply()
    }
    val StringWidgetPosition = "currentWidgetString1"
    fun getStringCurrentWidgetPosition(): String?{
        return settingPref.getString(StringWidgetPosition,"Widget/widget1/widget_2.webp")
    }

    fun setStringCurrentWidgetPosition(current: String?){
        settingPref.edit().putString(StringWidgetPosition,current).apply()
    }
}


