package com.martin.preventapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.martin.preventapp.R
import com.martin.preventapp.controller.seller.interfaces.OrdersInterface
import com.martin.preventapp.controller.seller.orders.OrdersController
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.OrderItem

class OrderAdapter (private val context: Context,
                    private val items: List<NewOrder>,
                    private val listener: OrderItemClickListener) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): NewOrder {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position)

        viewHolder.tvClient.text = item.client.name
        viewHolder.tvSeller.text = item.seller
        viewHolder.tvDate.text = "Estado: ${item.date}"
        viewHolder.tvState.text = "Estado: ${item.state}"
        viewHolder.actionButton.setOnClickListener {
            listener.onOrderItemClicked(item)
        }

        return view
    }

    private class ViewHolder(view: View) {
        val tvClient: TextView = view.findViewById(R.id.client)
        val tvSeller: TextView = view.findViewById(R.id.seller)
        val tvDate: TextView = view.findViewById(R.id.date)
        val tvState: TextView = view.findViewById(R.id.state)
        val actionButton: ImageButton = view.findViewById(R.id.actionButton)
    }
}

interface OrderItemClickListener {
    fun onOrderItemClicked(item: NewOrder)
}