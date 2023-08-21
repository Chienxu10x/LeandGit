package com.example.appcc

import android.content.Context
import android.content.SharedPreferences
import com.example.appcc.data.AuthReponsetory
import com.example.appcc.data.TimeLineRepository
import com.example.appcc.data.TimeLineRepositoryImpl
import com.example.appcc.data_login.OnSignInStartedListener
import com.example.appcc.data_login.OnSignInStartedListenerImpl
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
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

    @Provides
    fun provideSignInStartedListener(@ApplicationContext context: Context): OnSignInStartedListener {
        return OnSignInStartedListenerImpl(context)
    }

    @Provides
    fun provideTimeline(database: FirebaseDatabase,storage:FirebaseStorage): TimeLineRepository {
        return TimeLineRepositoryImpl(database,storage)
    }

    @Provides
    fun providesFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }
}
