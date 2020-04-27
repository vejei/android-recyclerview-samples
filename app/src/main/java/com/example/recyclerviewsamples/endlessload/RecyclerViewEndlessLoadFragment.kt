package com.example.recyclerviewsamples.endlessload

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsamples.R

/*
 * Reference:
 * https://stackoverflow.com/a/26643292/7706851
 * https://stackoverflow.com/a/33941753/7706851
 */
class RecyclerViewEndlessLoadFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: ExtraAdapter

    private var canLoad = true
    private var pastVisibleItems = 0
    private var visibleItemCount = 0
    private var totalItemCount:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view_endless_load, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)

        layoutManager = LinearLayoutManager(context)
        adapter = ExtraAdapter()

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        // https://stackoverflow.com/a/26643292/7706851
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
//                    Log.d(TAG, "scroll down.")
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                    if (canLoad) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.d(TAG, "loading start.")
                            canLoad = false
                            // load more
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
                                    canLoad = true
                                    Log.d(TAG, "loading end.")
                                }
                            }).start()
                        }
                    }
                }
            }
        })

        // https://stackoverflow.com/a/33941753/7706851
        /*recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastCompletelyVisibleItemPosition() == (adapter.getDataSize() - 1)) {
                    Log.d(TAG, "load more start.")
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
                        }
                    }).start()
                }
            }
        })*/
    }

    companion object {
        val TAG = RecyclerViewEndlessLoadFragment::class.java.simpleName
    }
}