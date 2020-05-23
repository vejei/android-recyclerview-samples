package com.example.recyclerviewsamples.grid

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsamples.R

class RecyclerViewFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var increaseColumnButton: Button
    private lateinit var reduceColumnButton: Button
    private var spanCount: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        increaseColumnButton = view.findViewById(R.id.button_grid_increase_column)
        reduceColumnButton = view.findViewById(R.id.button_grid_reduce_column)

        val layoutManager = GridLayoutManager(context, spanCount)
        val adapter = RecyclerViewAdapter()
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        var itemDecoration = GapItemDecoration(spanCount, TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 20f, context?.resources?.displayMetrics
        ).toInt(), false)
        recyclerView.addItemDecoration(itemDecoration)

        increaseColumnButton.setOnClickListener {
            spanCount++
            layoutManager.spanCount = spanCount
            recyclerView.layoutManager = layoutManager
            recyclerView.removeItemDecoration(itemDecoration)
            itemDecoration.spanCount = spanCount
            recyclerView.addItemDecoration(itemDecoration)
        }

        reduceColumnButton.setOnClickListener {
            spanCount--
            if (spanCount <= 1) spanCount = 1
            layoutManager.spanCount = spanCount
            recyclerView.layoutManager = layoutManager
            recyclerView.removeItemDecoration(itemDecoration)
            itemDecoration.spanCount = spanCount
            recyclerView.addItemDecoration(itemDecoration)
        }
    }
}

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_grid_recycler_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return 30
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}

// https://stackoverflow.com/a/55124012/7706851
// https://stackoverflow.com/a/30701422/7706851