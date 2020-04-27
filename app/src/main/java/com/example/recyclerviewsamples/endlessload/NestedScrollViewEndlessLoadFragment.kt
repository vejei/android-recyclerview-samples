package com.example.recyclerviewsamples.endlessload

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsamples.R

/*
Reference:
https://stackoverflow.com/a/41262612/7706851
 */
class NestedScrollViewEndlessLoadFragment : Fragment() {
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: ExtraAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nested_scroll_view_endless_load, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nestedScrollView = view.findViewById(R.id.nested_scroll_view)
        recyclerView = view.findViewById(R.id.recycler_view)

        layoutManager = LinearLayoutManager(context)
        adapter = ExtraAdapter()

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
            if (v?.getChildAt(v.childCount - 1) != null) {
                if (scrollY >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight)
                    && scrollY > oldScrollY) {
                    Log.d(TAG, "loading start.")
                    adapter.onLoading()
                    Thread(Runnable {
                        Thread.sleep(3000)
                        val newData = mutableListOf<String>(
                            "Apple", "Banana", "Blueberry", "Cherry", "Coconut", "Durian",
                            "Fig", "Grape", "Haw", "Kiwi"
                        )
                        activity?.runOnUiThread {
                            adapter.append(newData)
                            adapter.onLoaded()
                            Log.d(TAG, "loading end.")
                        }
                    }).start()
                }
            }
        })
    }

    companion object {
        val TAG = NestedScrollViewEndlessLoadFragment::class.java.simpleName
    }
}