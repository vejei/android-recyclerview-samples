package com.example.recyclerviewsamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.recyclerviewsamples.grid.RecyclerViewFragment
import com.example.recyclerviewsamples.grid.GridFragment
import com.example.recyclerviewsamples.grid.GridViewFragment
import com.example.recyclerviewsamples.itemdecoration.DividerFragment
import com.example.recyclerviewsamples.itemdecoration.GapFragment
import com.example.recyclerviewsamples.itemdecoration.ItemDecorationFragment

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
