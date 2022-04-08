package com.jasontsh.interviewkickstart.horizontallistexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.list)
        val listHeader : TextView = view.findViewById(R.id.list_header)
        listHeader.text = "1"
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS)
            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    listHeader.text = ((layoutManager as LinearLayoutManager)
                        .findFirstCompletelyVisibleItemPosition() + 1).toString()
                }
            })
        }
        val viewPager : ViewPager2 = view.findViewById(R.id.viewpager)
        viewPager.adapter = ScreenSlidePagerAdapter(this)
        val viewPagerHeader : TextView = view.findViewById(R.id.viewpager_header)
        viewPagerHeader.text = "1"
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewPagerHeader.text = (position + 1).toString()
            }
        })
        return view
    }

    private inner class ScreenSlidePagerAdapter(fa: Fragment) :
        FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = 50

        override fun createFragment(position: Int): Fragment {
            val fragment = ItemFragment()
            val bundle = Bundle()
            bundle.putInt(Constants.FRAGMENT_INDEX, position)
            fragment.arguments = bundle
            return fragment
        }
    }

}