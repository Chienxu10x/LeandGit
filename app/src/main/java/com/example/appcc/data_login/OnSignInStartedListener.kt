package com.example.appcc.data_login

import com.google.android.gms.auth.api.signin.GoogleSignInClient

interface OnSignInStartedListener {
    fun onSignInStarted(client: GoogleSignInClient?)
}