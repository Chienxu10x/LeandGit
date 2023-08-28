package com.example.appcc.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.adapter.FavoriteAdapter
import com.example.appcc.databinding.FragmentMyFavoriteBinding
import com.example.appcc.utils.UiState
import com.example.appcc.viewmodel.AuthViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFavoriteFragment : Fragment() {
    private lateinit var binding: FragmentMyFavoriteBinding

    private val authViewModel:AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imageBack.setOnClickListener {
            onBackPressed()
        }
        val user= Firebase.auth.currentUser
        if (user==null){
            binding.loading.visibility=View.GONE
            val toast = Toast(requireActivity())
            toast.duration = Toast.LENGTH_SHORT

            val inflater = requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout = inflater.inflate(R.layout.custom_toast, null)

// Customize the content of the Toast layout here if needed
            val toastText = layout.findViewById<TextView>(R.id.toast_text)
            toastText.text = "Please login"

            toast.view = layout
            toast.setGravity(Gravity.CENTER, 0, 0) // Set gravity to center

            toast.show()
            return
        }
        authViewModel.getFavorite()
        super.onViewCreated(view, savedInstanceState)
        val favoriteAdapter=FavoriteAdapter(requireActivity())
        binding.recyclerviewTheme.adapter=favoriteAdapter
        authViewModel.favorite.observe(viewLifecycleOwner){state->
            when(state){
                is UiState.Loading ->{
                    binding.loading.visibility=View.VISIBLE
                    Log.d("TAG", "onViewCreated: ")
                }
                is UiState.Failure ->{

                }
                is UiState.Success->{
                    binding.loading.visibility=View.GONE
                    favoriteAdapter.updateFavorite(state.data.toMutableList())
                }
                else -> {}
            }
        }
    }
    fun onSetupView(): MyFavoriteFragment{
        return MyFavoriteFragment()
    }
    companion object {

    }
    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            act.window.statusBarColor = ContextCompat.getColor(act, R.color.blue)
            (act as MainActivity).removeFragment(this)

        }
    }
}