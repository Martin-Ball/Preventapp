package com.martin.preventapp.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.martin.preventapp.R

class ProductAdapter(context: Context, items: List<String>) : ArrayAdapter<String>(context, R.layout.list_item, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = getItem(position)
        textView.setTextColor(Color.WHITE)
        return view
    }
}