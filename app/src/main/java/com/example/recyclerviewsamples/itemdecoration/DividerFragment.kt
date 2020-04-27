package com.example.recyclerviewsamples.itemdecoration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsamples.R
import com.example.recyclerviewsamples.StringAdapter

class DividerFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_divider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)

        val layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = StringAdapter()

        // Using custom ItemDecoration, the DividerDecoration would ignore last item
        recyclerView.addItemDecoration(DividerDecoration(context).apply {
            setDrawable(ContextCompat.getDrawable(context!!, R.drawable.divider))
        })

        /*
        or use the one which build in sdk. Calling default constructor would use default divider,
        if you want to use your divider, you can call setDrawable() by pass the drawable,
        but it would add divider for the last item.
         */
        /*recyclerView.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation).apply {
            setDrawable(ContextCompat.getDrawable(context!!, R.drawable.divider)!!)
        })*/
    }

}