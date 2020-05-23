package com.example.recyclerviewsamples

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StringAdapter : RecyclerView.Adapter<StringAdapter.ViewHolder>() {
    private val data = mutableListOf<String>(
        "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth",
        "Tenth", "Eleventh", "Twelfth"
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val contentTextView = itemView.findViewById<TextView>(R.id.text_view_content)

        fun bind(item: String) {
            contentTextView.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}