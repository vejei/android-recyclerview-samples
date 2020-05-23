package com.example.recyclerviewsamples.actionmode

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.recyclerviewsamples.R

class ListViewActionModeFragment : Fragment() {
    private lateinit var listView: ListView
    private var actionMode: ActionMode? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_view_action_mode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = view.findViewById(R.id.list_view)
        val adapter = ListViewAdapter()
        listView.adapter = adapter

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                Toast.makeText(context, "clicked $position", Toast.LENGTH_SHORT).show()
            }

        with(listView) {
            choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
            setMultiChoiceModeListener(object : AbsListView.MultiChoiceModeListener {
                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    return when (item?.itemId) {
                        R.id.menu_delete_selected -> {
                            adapter.deleteSelected()
                            mode?.finish()
                            return true
                        }
                        R.id.menu_select_all -> {
                            val count = listView.adapter.count

                            // If all items have been selected, then unselected all items, otherwise select all items.
                            if (adapter.allSelected()) {
                                adapter.unselectedAll()
                                for (i in 0 until count) {
                                    listView.setItemChecked(i, false)
                                }
                            } else {
                                adapter.selectAll()
                                for (i in 0 until count) {
                                    listView.setItemChecked(i, true)
                                }
                            }

                            // update title
                            val selectedItemCount = adapter.getSelectedItemCount()
                            mode?.title = if (selectedItemCount == 0) "" else "Selected $selectedItemCount"
                            return true
                        }
                        else -> false
                    }
                }

                override fun onItemCheckedStateChanged(
                    mode: ActionMode?,
                    position: Int,
                    id: Long,
                    checked: Boolean
                ) {
                    if (checked) {
                        adapter.selectedItem(position)
                    } else {
                        adapter.unselectedItem(position)
                    }

                    // update title
                    val selectedItemCount = adapter.getSelectedItemCount()
                    mode?.title = if (selectedItemCount == 0) "" else "Selected $selectedItemCount"
                }

                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    mode?.menuInflater?.inflate(R.menu.action_mode, menu)
                    actionMode = mode
                    adapter.actionModeEnabled = true
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return false
                }

                override fun onDestroyActionMode(mode: ActionMode?) {
                    adapter.reset()
                    actionMode = null
                }
            })
        }
    }
}

class ListViewAdapter : BaseAdapter() {
    private val data = mutableListOf(
        "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth", "Tenth"
    )
    private val selectedItems = mutableListOf<String>()
    var actionModeEnabled = false

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView: View = convertView ?: LayoutInflater.from(parent?.context).inflate(
                R.layout.item_image_text, parent, false
            )
        rowView.findViewById<TextView>(R.id.text_view_content).text = getItem(position)

        rowView.background = if (actionModeEnabled) {
            ContextCompat.getDrawable(rowView.context, R.drawable.action_mode_background)
        } else {
            ContextCompat.getDrawable(rowView.context, R.drawable.ripple_background)
        }

        return rowView
    }

    override fun getItem(position: Int): String {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    fun selectedItem(position: Int) {
        val item = data[position]
        if (!selectedItems.contains(item)) selectedItems.add(item)
    }

    fun unselectedItem(position: Int) {
        val item = data[position]
        if (selectedItems.contains(item)) selectedItems.remove(item)
    }

    fun getSelectedItemCount(): Int = selectedItems.size

    fun selectAll() {
        data.forEachIndexed { index, s ->
            selectedItem(index)
        }
    }

    fun unselectedAll() {
        selectedItems.clear()
    }

    fun deleteSelected() {
        selectedItems.forEach {
            data.remove(it)
            notifyDataSetChanged()
        }
    }

    fun allSelected(): Boolean {
        return selectedItems.size == data.size
    }

    fun reset() {
        selectedItems.clear()
        actionModeEnabled = false
    }
}