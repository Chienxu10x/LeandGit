package com.example.appcc.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appcc.Application
import com.example.appcc.R
import com.example.appcc.base.BaseViewModel
import com.example.appcc.data_login.OnSignInStartedListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val app: Application, private val listener: OnSignInStartedListener): BaseViewModel() {
    private var auth: FirebaseAuth = Firebase.auth

    private val _currentUser = MutableLiveData<FirebaseUser?>()

    val currentUser: MutableLiveData<FirebaseUser?> = _currentUser
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(app.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    private val googleSignInClient = GoogleSignIn.getClient(app, gso)


    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                _currentUser.value = auth.currentUser
            } else {
                _currentUser.value = null
            }
        }
    }
    fun signIn() {
        listener.onSignInStarted(googleSignInClient)
    }
    class MainActivityViewModelFactory(
        private val application: Application,
        private val listener: OnSignInStartedListener
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {

                return AuthViewModel(application, listener) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
