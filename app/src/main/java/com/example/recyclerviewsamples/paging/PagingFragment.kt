package com.example.recyclerviewsamples.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsamples.R
import com.example.recyclerviewsamples.StringAdapter

class PagingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_paging, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_first)
        firstRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,
            false)
        firstRecyclerView.adapter =
            StringAdapter()

        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(firstRecyclerView)

        val secondRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_second)
        secondRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,
            false)
        secondRecyclerView.adapter =
            PagingAdapter()

        val linearSnapHelper = LinearSnapHelper()
        linearSnapHelper.attachToRecyclerView(secondRecyclerView)
    }
}

class PagingAdapter : RecyclerView.Adapter<PagingAdapter.ViewHolder>() {
    private val data = mutableListOf<String>(
        "First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth",
        "Tenth", "Eleventh", "Twelfth", "Thirteenth", "Fourteenth", "Fifteenth"
    )

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var contentTextView = itemView.findViewById<TextView>(R.id.text_view_content)

        fun bind(item: String) {
            contentTextView.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_paging, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}