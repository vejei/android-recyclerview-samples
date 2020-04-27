package com.example.recyclerviewsamples.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsamples.R
import com.example.recyclerviewsamples.StringAdapter

/*
Reference:
https://stackoverflow.com/a/54668740/7706851
https://proandroiddev.com/enter-animation-using-recyclerview-and-layoutanimation-part-1-list-75a874a5d213
 */
class ItemEnterAnimationFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_enter_animation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = StringAdapter()
        // Set layout animation here, or set android:layoutAnimation attribute in layout.
        recyclerView.layoutAnimation = AnimationUtils.loadLayoutAnimation(context,
            R.anim.layout_animation
        )

        // Note: You have to call recyclerView.scheduleLayoutAnimation() after call adapter.notifyDataSetChanged().
        // Because calling adapter.notifyDataSetChanged() would cancel the animation.
    }
}