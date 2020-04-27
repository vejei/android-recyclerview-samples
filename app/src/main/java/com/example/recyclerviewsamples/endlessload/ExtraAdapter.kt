package com.example.recyclerviewsamples.endlessload

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsamples.R

class ExtraAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data = mutableListOf<String>(
        "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth",
        "Tenth", "Eleventh", "Twelfth"
    )
    private lateinit var statusViewHolder: StatusViewHolder

    inner class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val contentTextView = itemView.findViewById<TextView>(R.id.text_view_content)

        fun bind(item: String) {
            contentTextView.text = item
        }
    }

    inner class StatusViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val loadingLayout = itemView.findViewById<ConstraintLayout>(R.id.layout_loading)

        fun onLoading() {
            loadingLayout.visibility = View.VISIBLE
        }

        fun onLoaded() {
            loadingLayout.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            DATA_VIEW_TYPE -> {
                DataViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item, parent, false)
                )
            }
            STATUS_VIEW_TYPE -> {
                statusViewHolder = StatusViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_adapter_loading, parent,false)
                )
                statusViewHolder
            }
            else -> DataViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return if (data.size == 0) 0 else data.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DataViewHolder) {
            holder.bind(data[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position >= (itemCount - 1)) STATUS_VIEW_TYPE else DATA_VIEW_TYPE
    }

    fun getDataSize(): Int = data.size

    fun append(data: MutableList<String>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun onLoading() {
        statusViewHolder.onLoading()
    }

    fun onLoaded() {
        statusViewHolder.onLoaded()
    }

    companion object {
        const val DATA_VIEW_TYPE = 0
        const val STATUS_VIEW_TYPE = 1
    }
}