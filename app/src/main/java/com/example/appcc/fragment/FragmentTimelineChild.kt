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
import androidx.recyclerview.widget.RecyclerView
import com.example.appcc.R
import com.example.appcc.activity.MainActivity
import com.example.appcc.adapter.RecyclerAdapterTheme
import com.example.appcc.adapter.RecyclerAdapterTimeline
import com.example.appcc.databinding.FragmentTimelineChildBinding
import com.example.appcc.model.ContentX
import com.example.appcc.utils.UiState
import com.example.appcc.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentTimelineChild : Fragment() {
    private lateinit var binding: FragmentTimelineChildBinding
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimelineChildBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        authViewModel.getTimeLine()
        binding.recyclerviewTimeline.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecyclerAdapterTimeline(requireActivity())
        binding.recyclerviewTimeline.adapter = adapter
        authViewModel.timeline.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    Log.d("TAG",
                        "onViewCreated: " +"loading"
                    )
                    binding.loading.visibility=View.VISIBLE
                }

                is UiState.Failure -> {
                    Log.d("TAG",
                        "onViewCreated: " +"loading"+state.error
                    )
//                    binding.progressBar.hide()
//                    toast(state.error)
                    Toast.makeText(requireActivity(), state.error, Toast.LENGTH_SHORT).show()
                }

                is UiState.Success -> {
//                    binding.progressBar.hide()
                    binding.loading.visibility=View.GONE
                    adapter.updateList(state.data.toMutableList())
                    Log.d("TAG",
                        "onViewCreated: " + state.data.toMutableList().size
                    )
//                    adapter.updateList(state.data.toMutableList())
                }
            }
        }
    }

    private fun toDetail(item: ContentX) {

    }
}