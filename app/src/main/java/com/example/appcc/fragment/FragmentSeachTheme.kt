package com.example.appcc.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.appcc.activity.MainActivity
import com.example.appcc.adapter.RecyclerAdapterTheme
import com.example.appcc.adapter.RecyclerAdapterTheme2
import com.example.appcc.base.BaseFragment
import com.example.appcc.databinding.FragmentFragmetSeachThemeBinding
import com.example.appcc.model.ContentX
import com.example.appcc.viewmodel.IconViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

class FragmentSeachTheme : BaseFragment(), RecyclerAdapterTheme2.ClickListener {
    private lateinit var binding: FragmentFragmetSeachThemeBinding
    private val iconViewModel : IconViewModel by activityViewModels()
    private lateinit var adapterTheme2: RecyclerAdapterTheme2

    override fun bindView() {

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }


    }

    override fun observeData() {
        iconViewModel.currentTheme.observe(this){

            val originalItemList = listOf<ContentX>(
                it.content[0],
                it.content[1],
                it.content[2],
                it.content[3],
                it.content[4],
                it.content[5],
                it.content[6],
                it.content[7],
                it.content[8],
                it.content[9]
            )
            adapterTheme2 = RecyclerAdapterTheme2(originalItemList, this)
//            adapterTheme2 = RecyclerAdapterTheme2(originalItemList)
            binding.recyclerviewTheme.adapter = adapterTheme2
//            Log.d("TAG", "observeData: "+"a5")
//
//            recyclerAdapterTheme.submitList(it.content)
//            binding.recyclerviewTheme.adapter = recyclerAdapterTheme
            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    val filteredList = originalItemList.filter { item ->
                        // You can customize the filtering logic here
                        item.title.contains(newText.orEmpty(), ignoreCase = true)
                    }
                    adapterTheme2.updateData(filteredList)
//                listTheme(newText)
                  
                    return true
                }

            })

        }






        iconViewModel.allTheme.observe(this){
            Log.d("TAG", "observeData: "+"a1")
            iconViewModel.getThemeByFilter(0)
            binding.tabMenu.removeAllTabs()
            it.contents.forEach{
                val tab = binding.tabMenu.newTab()
                tab.text = it.title
//                Log.d("TAG", "observeData: "+it.content.get(0).previews)
                binding.tabMenu.addTab(tab)
            }
        }
        iconViewModel.loadAllResource(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFragmetSeachThemeBinding.inflate(layoutInflater)
        return binding.root
    }
    private fun onBackPressed() {
        activity?.let { act ->
            act.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            act.window.statusBarColor = ContextCompat.getColor(act, R.color.blue)
            (act as MainActivity).removeFragment(this)
        }
    }

    fun replaceFragment(){
        parentFragmentManager
    }

    override fun onClickListener(item: ContentX) {
        activity?.let {act->
            var fragmentGetTheme: FragmentGetTheme = FragmentGetTheme(item)
            (act as MainActivity).replaceFragment(fragmentGetTheme)
        }
    }

}