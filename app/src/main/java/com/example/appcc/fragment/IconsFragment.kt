package com.example.appcc.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcc.R
import com.example.appcc.adapter.OnTabNameChangedListener
import com.example.appcc.adapter.RecyclerAdapterTheme
import com.example.appcc.adapter.RecyclerViewAdapterIcon


class IconsFragment : Fragment(), OnTabNameChangedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_icons, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            val list : List<String> = listOf(
                "Chuỗi 1",
                "Chuỗi 1",


                )

            val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview_icon)

            val adapter = RecyclerViewAdapterIcon(list)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onTabNameChanged(position: Int, newName: String) {
    }


}