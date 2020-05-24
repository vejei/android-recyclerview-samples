package com.example.recyclerviewsamples.gesture

import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsamples.R
import com.google.android.material.snackbar.Snackbar

class VerticalListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vertical_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val adapter = VerticalListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        val itemTouchHelperCallback = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                // The direction of the drag gesture.
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN

                // The direction of the swipe gesture
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END

                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.onItemMoved(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.onItemSwiped(viewHolder.adapterPosition)
                // show snack bar
                val rootView = view.findViewById<LinearLayout>(R.id.root)
                val snackbar = Snackbar.make(rootView, "Deleted", Snackbar.LENGTH_LONG)
                snackbar.setAction("Undo", View.OnClickListener {
                    adapter.undoSwipe()
                })
                snackbar.show()
            }

            // The method will be called on gesture start.
            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                Log.d(TAG, "onSelectedChanged")
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    if (viewHolder is VerticalListAdapter.ViewHolder) {
                        viewHolder.onSelectedChanged(actionState)
                    }
                }
                super.onSelectedChanged(viewHolder, actionState)
            }

            // The method will be called on gesture end.
            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                Log.d(TAG, "clearView")
                super.clearView(recyclerView, viewHolder)
                if (viewHolder is VerticalListAdapter.ViewHolder) {
                    viewHolder.onClearView()
                }
            }

            // The method will be called during dragging or swiping.
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                Log.d(TAG, "onChildDraw")
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        adapter.onDragStartListener = object : VerticalListAdapter.OnDragStartListener {
            override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                itemTouchHelper.startDrag(viewHolder)
            }
        }
    }

    companion object {
        val TAG = VerticalListFragment::class.java.simpleName
    }
}