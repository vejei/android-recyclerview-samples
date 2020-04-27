package com.example.recyclerviewsamples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.recyclerviewsamples.grid.GridFragment
import com.example.recyclerviewsamples.itemdecoration.ItemDecorationFragment

class MainFragment : Fragment() {
    private lateinit var itemDecorationButton: Button
    private lateinit var gridListButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemDecorationButton = view.findViewById(R.id.button_main_item_decoration)
        gridListButton = view.findViewById(R.id.button_main_grid_list)

        itemDecorationButton.setOnClickListener {
            (activity as MainActivity).addFragment(ItemDecorationFragment())
        }

        gridListButton.setOnClickListener {
            (activity as MainActivity).addFragment(GridFragment())
        }
    }
}