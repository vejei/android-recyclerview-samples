package com.example.recyclerviewsamples.grid

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GapItemDecoration(
    var spanCount: Int, private val gap: Int, private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val rowIndex = position % spanCount

        if (includeEdge) {
            outRect.left = gap - rowIndex * gap / spanCount // gap - rowIndex * (1f / spanCount) * gap
            outRect.right = (rowIndex + 1) * gap / spanCount // (rowIndex + 1) * (1f / spanCount) * gap
            if (position < spanCount) {
                outRect.top = gap
            }
            outRect.bottom = gap
        } else {
            outRect.left = rowIndex * gap / spanCount
            outRect.right = gap - (rowIndex + 1) * gap / spanCount
            if (position >= spanCount) {
                outRect.top = gap
            }
        }
    }
}