package com.example.recyclerviewsamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.recyclerviewsamples.actionmode.ActionModeFragment
import com.example.recyclerviewsamples.actionmode.ListViewActionModeFragment
import com.example.recyclerviewsamples.actionmode.RecyclerViewActionModeFragment
import com.example.recyclerviewsamples.animation.ItemEnterAnimationFragment
import com.example.recyclerviewsamples.endlessload.EndlessLoadFragment
import com.example.recyclerviewsamples.endlessload.NestedScrollViewEndlessLoadFragment
import com.example.recyclerviewsamples.endlessload.RecyclerViewEndlessLoadFragment
import com.example.recyclerviewsamples.gesture.DragAndDropFragment
import com.example.recyclerviewsamples.gesture.GestureFragment
import com.example.recyclerviewsamples.grid.RecyclerViewFragment
import com.example.recyclerviewsamples.grid.GridFragment
import com.example.recyclerviewsamples.grid.GridViewFragment
import com.example.recyclerviewsamples.itemdecoration.DividerFragment
import com.example.recyclerviewsamples.itemdecoration.GapFragment
import com.example.recyclerviewsamples.itemdecoration.ItemDecorationFragment
import com.example.recyclerviewsamples.staggeredgrid.StaggeredGridFragment
import com.example.recyclerviewsamples.staggeredgrid.StaggeredGridLayoutManagerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, MainFragment())
                    .commit()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val titleRes = when (supportFragmentManager.findFragmentById(R.id.fragment_container)) {
                is MainFragment -> R.string.app_name
                is ItemDecorationFragment, is DividerFragment, is GapFragment -> R.string.main_item_decoration_button_text
                is GridFragment, is RecyclerViewFragment, is GridViewFragment -> R.string.main_grid_list_button_text
                is StaggeredGridFragment, is StaggeredGridLayoutManagerFragment -> R.string.main_staggered_grid_button_text
                is EndlessLoadFragment, is RecyclerViewEndlessLoadFragment, is NestedScrollViewEndlessLoadFragment -> R.string.main_endless_load_button_text
                is ItemEnterAnimationFragment -> R.string.main_item_enter_animation_button_text
                is ActionModeFragment, is ListViewActionModeFragment, is RecyclerViewActionModeFragment -> R.string.main_action_mode_button_text
                is GestureFragment, is DragAndDropFragment -> R.string.main_item_gesture_button_text
                else -> R.string.app_name
            }
            supportActionBar?.setTitle(titleRes)
        }
    }

    fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
