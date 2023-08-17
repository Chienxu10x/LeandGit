package com.example.appcc.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentProfileUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileUserFragment : BaseFragment() {
    private lateinit var binding: FragmentProfileUserBinding
//    private lateinit var auth: FirebaseAuth

    override fun bindView() {
//        auth=FirebaseAuth.getInstance()
        val user = Firebase.auth.currentUser
        if (user != null) {
            binding.edtName.setText(user.displayName)
        }
        binding.apply {
            imageBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun observeData() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProfileUserBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }
    fun onSetupView(): ProfileUserFragment{
        return ProfileUserFragment()
    }
    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            act.window.statusBarColor = ContextCompat.getColor(act, R.color.blue)
            (act as MainActivity).removeFragment(this)

        }
    }
    companion object {

    }
}