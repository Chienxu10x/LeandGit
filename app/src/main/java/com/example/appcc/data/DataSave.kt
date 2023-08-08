package com.example.appcc.data

import com.example.appcc.Application
import com.example.appcc.utils.Const.LANGUAGE_NOW

object DataSave {
    var language: String?
        get() {
            return Application.dataStore.getString(LANGUAGE_NOW, "en")
        }
        set(value) {
            Application.dataStore.putString(LANGUAGE_NOW, value)
        }
}