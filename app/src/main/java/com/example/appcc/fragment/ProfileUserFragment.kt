package com.example.appcc.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentProfileUserBinding
import com.example.appcc.utils.UiState
import com.example.appcc.viewmodel.AuthViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.FileNotFoundException

class ProfileUserFragment : BaseFragment() {
    private lateinit var binding: FragmentProfileUserBinding

    //    private lateinit var auth: FirebaseAuth
    private val auth: AuthViewModel by viewModels()
    private var uri: Uri? = null
    override fun bindView() {
        val user = Firebase.auth.currentUser

        val avarta=arguments?.getString("avarta")
            Glide.with(requireActivity()).load(avarta).error(R.drawable.ic_account).into(binding.imgAccount)
            binding.loading.visibility=View.GONE

        if (user != null) {
            binding.edtName.setText(user.displayName)
        }
        binding.apply {
            imageBack.setOnClickListener {
                onBackPressed()
            }
            imgAccount.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                launcher.launch(intent)
            }
            btnSave1.setOnClickListener {
                Log.d("TAG", "bindView: "+uri)
                uri?.let { it1 -> auth.updateProfile(it1) }
                auth.updateProfile.observe(viewLifecycleOwner) {state->
                    when(state){
                        is UiState.Loading->{
                        binding.loading.visibility=View.VISIBLE
                        }
                        is UiState.Failure->{

                        }
                        is UiState.Success->{
                            binding.loading.visibility=View.GONE
                            Toast.makeText(requireActivity(),"Thanh cong",Toast.LENGTH_SHORT).show()
                        }
                        else -> {}
                    }

                }
            }
        }
    }

    override fun observeData() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileUserBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    fun onSetupView(): ProfileUserFragment {
        return ProfileUserFragment()
    }

    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            (act as MainActivity).removeFragment(this)
            val fargmentset=SettingFragment().onSetupView()
            (act as MainActivity).replaceFragment(fargmentset)

        }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                uri = result.data?.getData()

            }
            try {
                val stream = uri?.let { activity?.contentResolver?.openInputStream(it) }
                val bitmap = BitmapFactory.decodeStream(stream)
                binding.imgAccount.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }

    companion object {

    }
}