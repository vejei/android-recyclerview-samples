package com.example.recyclerviewsamples.gesture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.recyclerviewsamples.MainActivity
import com.example.recyclerviewsamples.R

class GestureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gesture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val verticalListButton = view.findViewById<Button>(R.id.button_gesture_vertical_list)
        val gridListButton = view.findViewById<Button>(R.id.button_gesture_grid_list)

        verticalListButton.setOnClickListener {
            (activity as MainActivity).addFragment(VerticalListFragment())
        }

        gridListButton.setOnClickListener {
            (activity as MainActivity).addFragment(GridListFragment())
        }
    }
}