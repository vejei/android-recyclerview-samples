package com.example.recyclerviewsamples.gesture

import android.annotation.SuppressLint
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsamples.R
import java.util.*

class VerticalListAdapter : RecyclerView.Adapter<VerticalListAdapter.ViewHolder>() {
    private val data = mutableListOf<String>(
        "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth"
    )
    var onDragStartListener: OnDragStartListener? = null
    private var recentlyDeletedItem: String? = null
    private var recentlyDeletedItemPosition: Int? = null

    interface OnDragStartListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val contentTextView: TextView = itemView.findViewById(R.id.text_view_content)
        private val handleImageView: ImageView = itemView.findViewById(R.id.image_view_handle)

        @SuppressLint("ClickableViewAccessibility")
        fun bind(item: String) {
            contentTextView.text = item

            // Add touch listener for the handle image, so that you could use the handle to drag
            // the item directly without long pressing.
            handleImageView.setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    onDragStartListener?.onStartDrag(this)
                }
                true
            }
        }

        fun onSelectedChanged(actionState: Int) {
            // Animating the item's translationZ attribute to indicate that the item can be dragged.
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                itemView.animate().translationZ(
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 8f,
                        itemView.context.resources.displayMetrics)
                ).setDuration(100).start()
            }
        }

        fun onClearView() {
            itemView.animate().translationZ(0f).setDuration(100).start()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_gesture_vertical_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun onItemMoved(fromPosition: Int, toPosition: Int) {
        // If fromPosition is less than toPosition, it means drag from top to bottom,
        // otherwise from bottom to top.
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(data, i, i+1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(data, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    fun onItemSwiped(position: Int) {
        recentlyDeletedItem = data[position]
        recentlyDeletedItemPosition = position
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun undoSwipe() {
        data.add(recentlyDeletedItemPosition!!, recentlyDeletedItem!!)
        notifyItemInserted(recentlyDeletedItemPosition!!)
    }
}

class GridListAdapter : RecyclerView.Adapter<GridListAdapter.ViewHolder>() {
    private val data = mutableListOf<String>(
        "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth",
        "Tenth", "Eleventh", "Twelfth", "Thirteenth", "Fourteenth", "Fifteenth", "Sixteenth",
        "Seventeenth", "Eighteenth"
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val contentTextView = itemView.findViewById<TextView>(R.id.text_view_content)

        fun bind(item: String) {
            contentTextView.text = item
        }

        fun onSelectedChanged(actionState: Int) {
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                itemView.animate().translationZ(
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 8f,
                        itemView.context.resources.displayMetrics)
                ).setDuration(100).start()
            }
        }

        fun onClearView() {
            itemView.animate().translationZ(0f).setDuration(100).start()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_gesture_grid_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Log.d("Adapter", "fromPosition: $fromPosition, toPosition: $toPosition")
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(data, i, i+1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(data, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }
}