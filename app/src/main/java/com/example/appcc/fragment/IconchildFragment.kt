package com.example.appcc.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcc.R
import com.example.appcc.adapter.RecyclerAdapterTimeline
import com.example.appcc.adapter.RecyclerAdapterTimelineIcon
import com.example.appcc.databinding.FragmentIconchildBinding
import com.example.appcc.utils.UiState
import com.example.appcc.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IconchildFragment : Fragment() {
    private lateinit var binding:FragmentIconchildBinding

    private val authViewModel:AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentIconchildBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel.getTimeLine()
        binding.recyclerviewTimeline.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecyclerAdapterTimelineIcon(requireActivity())
        binding.recyclerviewTimeline.adapter = adapter
        authViewModel.timeline.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
//                    binding.progressBar.show()
                    binding.loading.visibility=View.VISIBLE
                }

                is UiState.Failure -> {
//                    binding.progressBar.hide()
//                    toast(state.error)
                    Toast.makeText(requireActivity(),state.error, Toast.LENGTH_SHORT).show()
                }

                is UiState.Success -> {
//                    binding.progressBar.hide()
                    binding.loading.visibility=View.GONE
                    adapter.updateList(state.data.toMutableList())
                    Log.d("TAG", "onViewCreated: "+state.data.toMutableList())
//                    adapter.updateList(state.data.toMutableList())
                }
            }
        }
    }
    companion object {

    }
}