package com.example.recyclerviewsamples.grid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.recyclerviewsamples.MainActivity
import com.example.recyclerviewsamples.R

class GridFragment : Fragment() {
    private lateinit var recyclerViewButton: Button
    private lateinit var gridViewButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewButton = view.findViewById(R.id.button_grid_recycler_view)
        gridViewButton = view.findViewById(R.id.button_grid_grid_view)

        recyclerViewButton.setOnClickListener {
            (activity as MainActivity).addFragment(RecyclerViewFragment())
        }

        gridViewButton.setOnClickListener {
            (activity as MainActivity).addFragment(GridViewFragment())
        }
    }
}