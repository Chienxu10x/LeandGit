
package com.example.appcc.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.appcc.data.DataRepository
import com.example.appcc.data.ResourceUtils
import com.example.appcc.extension.dataStore
import com.example.appcc.model.Content
import com.example.appcc.model.Theme
import com.example.appcc.model.Widget
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class ReadContentRepo @Inject constructor(
) {
    fun readDataIcon(context: Context): Theme {
        val res = ResourceUtils.loadData(context, "contents.json")
        return Gson().fromJson(res, Theme::class.java)
    }

    fun readDataWidget(context: Context): Widget {
        val res = ResourceUtils.loadData(context, "widget.json")
        return Gson().fromJson(res, Widget::class.java)
    }
    suspend fun setLanguage(context: Context,lang: String) {
        context.dataStore.edit {
            it[LANGUAGE] = lang
        }
    }

    suspend fun searchThemes(context: Context, query: String): List<Content> {
        val allThemes = readDataIcon(context)
        val results = mutableListOf<Content>()

        for (theme in allThemes.contents) {
            if (theme.title.contains(query, true) || theme.title.contains(query, true)) {
                results.add(theme)
            }
        }

        return results
    }

    companion object{
        val LANGUAGE = stringPreferencesKey("LANGUAGE")
    }
}