package com.example.appcc.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.appcc.BuildConfig
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseFragment
import com.example.appcc.data.DataSave
import com.example.appcc.databinding.FragmentSettingBinding
import com.example.appcc.dialog.SetupLanguageDialog
import com.example.appcc.utils.resetActivity
import com.example.appcc.utils.shareApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment() {
    private lateinit var binding: FragmentSettingBinding
    override fun bindView() {
        val user = Firebase.auth.currentUser
        if (user!=null){
            binding.lnLogout.setVisibility(View.VISIBLE)
            binding.tvName.setText(user.displayName)
        }else{
            binding.lnLogout.setVisibility(View.GONE)
            binding.tvName.setText(getString(R.string.login_sign_))
        }
        binding.apply {
            loading.setVisibility(View.GONE)
            imageBack.setOnClickListener {
                onBackPressed()
            }
            lnLoginSignup.setOnClickListener {
                activity?.let { act ->
                    if (user != null) {
                        val profile =ProfileUserFragment().onSetupView()
                        (act as MainActivity).replaceFragment(profile)
                    } else {
                        val privacyView: PrivacyPolicyFragment =PrivacyPolicyFragment().onSetupView()
                        (act as MainActivity).replaceFragment(privacyView)
                    }


                }
            }
            lnEarnReward.setOnClickListener {

            }
            lnFavourite.setOnClickListener {

            }
            lnMyTimelines.setOnClickListener {

            }
            lnPolicy.setOnClickListener {
                activity?.let { act ->
                    val privacyView: FragmentPrivacyPolicy = FragmentPrivacyPolicy().onSetupView()
                    (act as MainActivity).replaceFragment(privacyView)
                }
//                SingletonScreen.getInstance().type = Screen.Document
            }
            lnTermOfUse.setOnClickListener {
                activity?.let { act ->
                    val termOfUseView: FragmentTermOfUse = FragmentTermOfUse().onSetupView()
                    (act as MainActivity).replaceFragment(termOfUseView)
                }
            }
            lnContact.setOnClickListener {

            }
            lnShare.setOnClickListener {
                requireContext().shareApp()
            }
            lnReview.setOnClickListener {

            }
            lnLanguage.setOnClickListener {
                val languageDialog =
                    SetupLanguageDialog(DataSave.language ?: "en", setupLanguage = ::setupLanguage)
                languageDialog.isCancelable = false
                languageDialog.show(childFragmentManager, DIALOG_LANGUAGE)
            }
            lnFAQ.setOnClickListener {

            }
            binding.lnLogout.setOnClickListener {
                loading.setVisibility(View.VISIBLE)
                val auth =FirebaseAuth.getInstance()
                auth.signOut()
                onBackPressed()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }
    override fun observeData() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            act.window.statusBarColor = ContextCompat.getColor(act, R.color.blue)
            (act as MainActivity).removeFragment(this)

        }
    }
    private fun setupLanguage(lang: String) {
        if (lang.isNotEmpty()) {
            DataSave.language = lang
            activity?.resetActivity()
        }
    }
    companion object {
        private const val DIALOG_LANGUAGE = "DIALOG_LANGUAGE"
    }
}