package com.example.recyclerviewsamples.actionmode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.recyclerviewsamples.MainActivity
import com.example.recyclerviewsamples.R

class ActionModeFragment : Fragment() {
    private lateinit var recyclerViewActionModeButton: Button
    private lateinit var listViewActionModeButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_action_mode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewActionModeButton = view.findViewById(R.id.button_recycler_view_action_mode)
        listViewActionModeButton = view.findViewById(R.id.button_list_view_action_mode)

        recyclerViewActionModeButton.setOnClickListener {
            (activity as MainActivity).addFragment(RecyclerViewActionModeFragment())
        }
        listViewActionModeButton.setOnClickListener {
            (activity as MainActivity).addFragment(ListViewActionModeFragment())
        }
    }
}