package com.example.appcc.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appcc.Application
import com.example.appcc.R
import com.example.appcc.base.BaseViewModel
import com.example.appcc.data.TimeLineRepository
import com.example.appcc.data_login.OnSignInStartedListener
import com.example.appcc.model.CommentModel
import com.example.appcc.model.ContentX
import com.example.appcc.model.UserModel
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
    private val _timeLine = MutableLiveData<UiState<MutableList<CommentModel>>>()

    val timeline: LiveData<UiState<MutableList<CommentModel>>>
        get() = _timeLine

    private val _addTimeLine = MutableLiveData<UiState<String>>()
    val addTimeline: LiveData<UiState<String>>
        get() = _addTimeLine


    fun getTimeLine() {
        _timeLine.value = UiState.Loading
        timeLineRepository.getTimeLine { _timeLine.value = it }
    }
    fun addTimeline(commentModel: CommentModel,id:String){
        _addTimeLine.value = UiState.Loading
        timeLineRepository.addTimeLine(commentModel,id) { _addTimeLine.value = it }
    }
    private val _addFavorite = MutableLiveData<UiState<String>>()

    private val _favorite=MutableLiveData<UiState<MutableList<ContentX>>>()
    val favorite:LiveData<UiState<MutableList<ContentX>>>
        get()=_favorite

    fun getFavorite(){
        _favorite.value=UiState.Loading
        timeLineRepository.getFavorite { _favorite.value=it }
    }
    val addFavorite:LiveData<UiState<String>>
        get() =_addFavorite

    fun addFavorite(contentX: ContentX,titleTheme:String){
        _addFavorite.value=UiState.Loading
        timeLineRepository.addFavorite(contentX,titleTheme){_addFavorite.value = it}
    }

    private val _mytimeLine = MutableLiveData<UiState<MutableList<CommentModel>>>()
    val mytimeline: LiveData<UiState<MutableList<CommentModel>>>
        get() = _mytimeLine

    fun geMytTimeLine() {
        _mytimeLine.value = UiState.Loading
        timeLineRepository.getMyimeLine { _mytimeLine.value = it }
    }


    private val _updateProfile = MutableLiveData<UiState<String>>()
    val updateProfile: LiveData<UiState<String>>
        get() = _updateProfile
    fun updateProfile(uri: Uri){
        _updateProfile.value = UiState.Loading
        timeLineRepository.updateProfile(uri) { _updateProfile.value = it }
    }

    private val _usermodel = MutableLiveData<UiState<MutableList<UserModel>>>()

    val userModel: LiveData<UiState<MutableList<UserModel>>>
        get() = _usermodel

    fun getMyUserModel() {
        _usermodel.value = UiState.Loading
        timeLineRepository.getUserModel { _usermodel.value = it }
    }

}
