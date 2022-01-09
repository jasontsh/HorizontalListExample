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
                    val firstView = getChildAt(0)
                    val idText : TextView = firstView.findViewById(R.id.item_number)
                    listHeader.text = idText.text
                }
            })
        }
        val viewPager : ViewPager = view.findViewById(R.id.viewpager)
        viewPager.adapter = ScreenSlidePagerAdapter(childFragmentManager)
        val viewPagerHeader : TextView = view.findViewById(R.id.viewpager_header)
        viewPagerHeader.text = "1"
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) = Unit

            override fun onPageSelected(position: Int) {
                viewPagerHeader.text = (position + 1).toString()
            }

            override fun onPageScrollStateChanged(state: Int) = Unit
        })
        return view
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int = 50

        override fun getItem(position: Int): Fragment {
            val fragment = ItemFragment()
            val bundle = Bundle()
            bundle.putInt(Constants.FRAGMENT_INDEX, position)
            fragment.arguments = bundle
            return fragment
        }
    }

}