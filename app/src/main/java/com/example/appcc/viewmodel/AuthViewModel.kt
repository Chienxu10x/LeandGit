package com.example.appcc.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appcc.Application
import com.example.appcc.R
import com.example.appcc.base.BaseViewModel
import com.example.appcc.data.TimeLineRepository
import com.example.appcc.data_login.OnSignInStartedListener
import com.example.appcc.model.ContentX
import com.example.appcc.utils.UiState
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val timeLineRepository: TimeLineRepository
) : BaseViewModel() {
    private val _timeLine = MutableLiveData<UiState<MutableList<ContentX>>>()

    val timeline: LiveData<UiState<MutableList<ContentX>>>
        get() = _timeLine

    private val _addTimeLine = MutableLiveData<UiState<String>>()
    val addTimeline: LiveData<UiState<String>>
        get() = _addTimeLine


    fun getTimeLine() {
        _timeLine.value = UiState.Loading
        timeLineRepository.getTimeLine { _timeLine.value = it }
    }
    fun addTimeline(contentX: ContentX){
        _addTimeLine.value = UiState.Loading
        timeLineRepository.addTimeLine(contentX) { _addTimeLine.value = it }
    }

}
