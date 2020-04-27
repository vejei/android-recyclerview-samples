package com.example.recyclerviewsamples.itemdecoration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.recyclerviewsamples.MainActivity
import com.example.recyclerviewsamples.R

/*
Reference:
https://stackoverflow.com/a/27037230/7706851
https://stackoverflow.com/a/30701422/7706851
 */
class ItemDecorationFragment : Fragment() {
    private lateinit var dividerButton: Button
    private lateinit var gapButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_decoration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dividerButton = view.findViewById(R.id.button_item_decoration_divider)
        gapButton = view.findViewById(R.id.button_item_decoration_gap)

        dividerButton.setOnClickListener {
            (activity as MainActivity).addFragment(DividerFragment())
        }
        gapButton.setOnClickListener {
            (activity as MainActivity).addFragment(GapFragment())
        }
    }
}