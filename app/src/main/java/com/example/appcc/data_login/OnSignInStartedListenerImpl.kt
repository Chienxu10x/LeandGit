package com.example.appcc.data_login

import android.content.Context
import com.example.appcc.fragment.PrivacyPolicyFragment
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class OnSignInStartedListenerImpl @Inject constructor(@ApplicationContext private val context: Context) : OnSignInStartedListener {
    override fun onSignInStarted(client: GoogleSignInClient?) {
//        client?.signInIntent?.let { context.startActivityForResult(it, PrivacyPolicyFragment.RC_SIGN_IN) }
    }
}