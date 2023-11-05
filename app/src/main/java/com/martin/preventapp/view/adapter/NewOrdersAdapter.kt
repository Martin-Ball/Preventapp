package com.martin.preventapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.NewOrdersController
import com.martin.preventapp.controller.seller.orders.OrdersController
import com.martin.preventapp.view.entities.OrderItem

class NewOrdersAdapter (private val context: Context, private val items: List<OrderItem>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): OrderItem {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_new_order, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position)

        viewHolder.client.text = item.client.name
        viewHolder.seller.text = item.seller
        viewHolder.actionButton.setOnClickListener {
            NewOrdersController.instance!!.setItemToDetail(item)
            NewOrdersController.instance!!.showFragmentDetail()
        }

        return view
    }

    private class ViewHolder(view: View) {
        val client: TextView = view.findViewById(R.id.client)
        val seller: TextView = view.findViewById(R.id.seller)
        val actionButton: ImageButton = view.findViewById(R.id.actionButton)
    }
}