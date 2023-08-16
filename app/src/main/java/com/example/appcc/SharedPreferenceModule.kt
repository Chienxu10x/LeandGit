package com.example.appcc

import android.content.Context
import android.content.SharedPreferences
import com.example.appcc.data.AuthReponsetory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {
    private const val mySharedPref = "AestheticSharedPreferences"

    //lưu data để set widget
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(mySharedPref, Context.MODE_PRIVATE)
    }

}
