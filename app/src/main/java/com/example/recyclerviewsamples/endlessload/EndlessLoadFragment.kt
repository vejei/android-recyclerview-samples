package com.example.recyclerviewsamples.endlessload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.recyclerviewsamples.MainActivity
import com.example.recyclerviewsamples.R

class EndlessLoadFragment : Fragment() {
    private lateinit var recyclerViewEndlessLoadButton: Button
    private lateinit var nestedScrollViewEndlessLoadButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_endless_load, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewEndlessLoadButton = view.findViewById(R.id.button_recycler_view_endless_load)
        nestedScrollViewEndlessLoadButton = view.findViewById(R.id.button_nested_scroll_view_endless_load)

        recyclerViewEndlessLoadButton.setOnClickListener {
            (activity as MainActivity).addFragment(RecyclerViewEndlessLoadFragment())
        }
        nestedScrollViewEndlessLoadButton.setOnClickListener {
            (activity as MainActivity).addFragment(NestedScrollViewEndlessLoadFragment())
        }
    }
}