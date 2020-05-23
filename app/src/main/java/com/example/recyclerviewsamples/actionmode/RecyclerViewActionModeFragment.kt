package com.example.recyclerviewsamples.actionmode

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsamples.R

class RecyclerViewActionModeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private var selectionTracker: SelectionTracker<Long>? = null
    private var actionMode: ActionMode? = null
    private lateinit var actionModeCallback: ActionMode.Callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) selectionTracker?.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        selectionTracker?.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recylcer_view_action_mode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)

        val adapter = RecyclerViewAdapter()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        selectionTracker = SelectionTracker.Builder<Long>(
            "string-list",
            recyclerView,
            StableIdKeyProvider(recyclerView),
            DetailsLookup(recyclerView),
            StorageStrategy.createLongStorage()
        ).build()
        selectionTracker?.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
            override fun onItemStateChanged(key: Long, selected: Boolean) {
                Log.d(TAG, "key: $key")
                if (adapter.itemCount != 0) {
                    if (selected) {
                        adapter.selectItem(key.toInt())
                    } else {
                        adapter.deselectItem(key.toInt())
                    }
                }

                if (actionMode == null && (selectionTracker?.hasSelection() == true)) {
                    (context as AppCompatActivity).startSupportActionMode(actionModeCallback)
                    adapter.actionModeEnabled = true
                    Log.d(TAG, "action mode start.")
                }
                val selectionSize = selectionTracker?.selection?.size()
                if (selectionSize == 0) {
                    actionMode?.finish()
                    Log.d(TAG, "actionMode is null? ${actionMode == null}, action mode finish.")
                } else {
                    actionMode?.title =
                        if (selectionSize == 0) "" else "Selected $selectionSize"
                }
            }
        })

        actionModeCallback = object : ActionMode.Callback {
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.menu_delete_selected -> {
                        adapter.deleteSelected()
                        selectionTracker?.clearSelection()
                        mode?.finish()
                        return true
                    }
                    R.id.menu_select_all -> {
                        val selectedItemSize = selectionTracker?.selection?.size()
                        if (selectedItemSize == adapter.itemCount) {
                            selectionTracker?.clearSelection()
                            adapter.deselectAll()
                            mode?.finish()
                        } else {
                            for (i in 0 until adapter.itemCount) {
                                if (selectionTracker?.isSelected(i.toLong()) == false) {
                                    selectionTracker?.select(i.toLong())
                                }
                            }
                            adapter.selectAll()
                        }
                        return true
                    }
                    else -> false
                }
            }

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                actionMode = mode
                mode?.menuInflater?.inflate(R.menu.action_mode, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                actionMode = null
                selectionTracker?.clearSelection()
                adapter.reset()
            }
        }
    }

    companion object {
        val TAG = RecyclerViewActionModeFragment::class.java.simpleName
    }
}

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val data = mutableListOf(
        "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth"
    )
    private val selectedItems = mutableListOf<String>()
    var actionModeEnabled = false

    init {
        setHasStableIds(true)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val contentTextView = itemView.findViewById<TextView>(R.id.text_view_content)

        fun bind(item: String, position: Int) {
            contentTextView.text = item

            itemView.background = if (actionModeEnabled) {
                ContextCompat.getDrawable(itemView.context, R.drawable.action_mode_background)
            } else {
                ContextCompat.getDrawable(itemView.context, R.drawable.ripple_background)
            }

            itemView.isActivated = isSelected(position)

            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "clicked $position", Toast.LENGTH_SHORT).show()
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> {
            return object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getSelectionKey(): Long? {
                    return itemId
                }

                override fun getPosition(): Int {
                    return adapterPosition
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_image_text, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun selectItem(position: Int) {
        if (position >= itemCount) return
        val item = data[position]
        if (selectedItems.contains(item)) selectedItems.remove(item)
        selectedItems.add(item)
    }

    fun deselectItem(position: Int) {
        if (position >= itemCount) return
        val item = data[position]
        if (selectedItems.contains(item)) selectedItems.remove(item)
    }

    fun deleteSelected() {
        selectedItems.forEach {
            data.remove(it)
        }
        notifyDataSetChanged()
    }

    fun selectAll() {
        data.forEachIndexed { index, s ->
            selectItem(index)
        }
        notifyDataSetChanged()
    }

    fun deselectAll() {
        selectedItems.clear()
        notifyDataSetChanged()
    }

    fun isSelected(position: Int): Boolean {
        return selectedItems.contains(data[position])
    }

    fun reset() {
        selectedItems.clear()
        actionModeEnabled = false
        notifyDataSetChanged()
    }
}

class DetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        val childView = recyclerView.findChildViewUnder(e.x, e.y)
        return if (childView != null) {
            val viewHolder = recyclerView.getChildViewHolder(childView)
                    as RecyclerViewAdapter.ViewHolder
            viewHolder.getItemDetails()
        } else {
            null
        }
    }
}