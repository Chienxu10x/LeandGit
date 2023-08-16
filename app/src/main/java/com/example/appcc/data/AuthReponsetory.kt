package com.example.appcc.data

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthReponsetory @Inject constructor(@ApplicationContext private val context: Context){
    fun signInWithGoogle(): Task<AuthResult> {
        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("516736252770-1gqqf5vbn9pehaugdhqgojtoi2m9t6nj.apps.googleusercontent.com") // Replace with your Web Client ID
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, signInOptions)
        val signInIntent = googleSignInClient.signInIntent

        // Assuming you have onActivityResult handling
        val signInResult = GoogleSignIn.getSignedInAccountFromIntent(signInIntent)
        val googleSignInAccount = signInResult.getResult(ApiException::class.java)

        return Firebase.auth.signInWithCredential(
            GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null)
        )
    }
}