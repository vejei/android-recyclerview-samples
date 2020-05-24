package com.example.recyclerviewsamples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.recyclerviewsamples.actionmode.ActionModeFragment
import com.example.recyclerviewsamples.animation.ItemEnterAnimationFragment
import com.example.recyclerviewsamples.endlessload.EndlessLoadFragment
import com.example.recyclerviewsamples.gesture.GestureFragment
import com.example.recyclerviewsamples.grid.GridFragment
import com.example.recyclerviewsamples.itemdecoration.ItemDecorationFragment
import com.example.recyclerviewsamples.paging.PagingFragment
import com.example.recyclerviewsamples.staggeredgrid.StaggeredGridFragment

class MainFragment : Fragment() {
    private lateinit var itemDecorationButton: Button
    private lateinit var gridListButton: Button
    private lateinit var staggeredGridButton: Button
    private lateinit var endlessLoadButton: Button
    private lateinit var itemEnterAnimationButton: Button
    private lateinit var actionModeButton: Button
    private lateinit var itemGestureButton: Button
    private lateinit var pagingButton: Button

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
        staggeredGridButton = view.findViewById(R.id.button_main_staggered_grid)
        endlessLoadButton = view.findViewById(R.id.button_main_endless_load)
        itemEnterAnimationButton = view.findViewById(R.id.button_main_item_enter_animation)
        actionModeButton = view.findViewById(R.id.button_main_action_mode)
        itemGestureButton = view.findViewById(R.id.button_main_item_gesture)
        pagingButton = view.findViewById(R.id.button_main_paging)

        itemDecorationButton.setOnClickListener {
            (activity as MainActivity).addFragment(ItemDecorationFragment())
        }

        gridListButton.setOnClickListener {
            (activity as MainActivity).addFragment(GridFragment())
        }

        staggeredGridButton.setOnClickListener {
            (activity as MainActivity).addFragment(StaggeredGridFragment())
        }

        endlessLoadButton.setOnClickListener {
            (activity as MainActivity).addFragment(EndlessLoadFragment())
        }

        itemEnterAnimationButton.setOnClickListener {
            (activity as MainActivity).addFragment(ItemEnterAnimationFragment())
        }

        actionModeButton.setOnClickListener {
            (activity as MainActivity).addFragment(ActionModeFragment())
        }

        itemGestureButton.setOnClickListener {
            (activity as MainActivity).addFragment(GestureFragment())
        }

        pagingButton.setOnClickListener {
            (activity as MainActivity).addFragment(PagingFragment())
        }
    }
}