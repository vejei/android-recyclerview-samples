package com.example.recyclerviewsamples.grid;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = GridSpacingItemDecoration.class.getSimpleName();

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, RecyclerView parent,
                               @NotNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column
        Log.d(TAG, "column value: " + column);

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)
            Log.d(TAG, "includeEdge, column: " + column + " outRect.left: " + outRect.left + ", outRect.right: " + outRect.right);

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            Log.d(TAG, "outRect.left: " + outRect.left + ", outRect.right: " + outRect.right);
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}
