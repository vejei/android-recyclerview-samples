package com.example.recyclerviewsamples.staggeredgrid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.recyclerviewsamples.MainActivity
import com.example.recyclerviewsamples.R

class StaggeredGridFragment : Fragment() {
    private lateinit var staggeredGridLayoutManagerButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_staggered_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        staggeredGridLayoutManagerButton = view.findViewById(R.id.button_staggered_grid_layout_manager)
        staggeredGridLayoutManagerButton.setOnClickListener {
            (activity as MainActivity).addFragment(StaggeredGridLayoutManagerFragment())
        }
    }
}