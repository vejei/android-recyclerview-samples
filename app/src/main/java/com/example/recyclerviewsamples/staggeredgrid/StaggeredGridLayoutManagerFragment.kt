package com.example.recyclerviewsamples.staggeredgrid

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recyclerviewsamples.R
import com.example.recyclerviewsamples.grid.GapItemDecoration

class StaggeredGridLayoutManagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_staggered_grid_layout_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val spanCount = 2

        recyclerView.layoutManager = StaggeredGridLayoutManager(spanCount, RecyclerView.VERTICAL)
        recyclerView.adapter =
            ImageAdapter()
        recyclerView.addItemDecoration(
            GapItemDecoration(
            spanCount,
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4f, context?.resources?.displayMetrics
            ).toInt(),
            true)
        )
    }
}