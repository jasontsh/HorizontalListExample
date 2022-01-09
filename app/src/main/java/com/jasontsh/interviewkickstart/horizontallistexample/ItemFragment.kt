package com.jasontsh.interviewkickstart.horizontallistexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jasontsh.interviewkickstart.horizontallistexample.Constants.FRAGMENT_INDEX

class ItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item, container, false)
        val index : Int = arguments?.get(FRAGMENT_INDEX) as Int
        val item = PlaceholderContent.ITEMS[index]
        view.findViewById<TextView>(R.id.item_number).text = item.id
        view.findViewById<TextView>(R.id.content).text = item.content
        return view
    }
}