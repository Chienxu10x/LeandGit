package com.example.appcc.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseFragment
import com.example.appcc.data_login.OnSignInStartedListener
import com.example.appcc.databinding.FragmentPrivacyPolicyBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrivacyPolicyFragment : BaseFragment() {
    private lateinit var binding: FragmentPrivacyPolicyBinding
//    private val authViewModel: AuthViewModel by lazy {
//        val factory = AuthViewModel.MainActivityViewModelFactory(
//            requireActivity().application, // Pass the application context here
//            object : OnSignInStartedListener {
//                override fun onSignInStarted(client: GoogleSignInClient?) {
//                    client?.signInIntent?.let { startActivityForResult(it, RC_SIGN_IN) }
//                }
//            }
//        )
//
//        ViewModelProvider(this, factory).get(AuthViewModel::class.java)
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrivacyPolicyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun bindView() {
        binding.apply {
            btnBack.setOnClickListener {
                onBackPressed()
            }
            btnGoogle.setOnClickListener {
//                authViewModel.signIn()
            }

//            authViewModel.currentUser.observe(requireActivity(), {
//                it?.let {
//                    Log.d("TAG", "bindView: "+it.displayName)
////                    binding.textView.text = it.displayName
//                }
//            })
        }

    }

    override fun observeData() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun onSetupView(): PrivacyPolicyFragment {
        return PrivacyPolicyFragment()
    }

    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            act.window.statusBarColor = ContextCompat.getColor(act, R.color.blue)
            (act as MainActivity).removeFragment(this)

        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == RC_SIGN_IN && resultCode == Activity.RESULT_OK && data != null) {
//
//            // this task is responsible for getting ACCOUNT SELECTED
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)!!
//
//                authViewModel.firebaseAuthWithGoogle(account.idToken!!)
//
//                Toast.makeText(requireActivity(), "Signed In Successfully", Toast.LENGTH_SHORT)
//                    .show()
//
//            } catch (e: ApiException) {
//                Toast.makeText(requireActivity(), e.localizedMessage, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


    companion object {
        private const val RC_SIGN_IN = 9001
    }
}