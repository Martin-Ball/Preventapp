package com.martin.preventapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.interfaces.OrderDetail
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.controller.delivery.orders.OrdersToDeliverController
import com.martin.preventapp.view.entities.NewOrder

class NewOrdersAdapter (
    private val context: Context,
    private val items: List<NewOrder>,
    private val userType: Int, //1 admin 2 Repartidor 3 Preventista
    private val orderController: OrderDetail
) : BaseAdapter() {
    private val selectedPositions = mutableSetOf<Int>()

    private fun toggleItemSelection(position: Int) {
        if (selectedPositions.contains(position)) {
            selectedPositions.remove(position)
        } else {
            selectedPositions.add(position)
        }
        notifyDataSetChanged()
    }

    fun clearSelection() {
        selectedPositions.clear()
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): NewOrder {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getSelectedItems(): List<NewOrder> {
        return selectedPositions.map { items[it] }
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
        viewHolder.date.text = item.date
        viewHolder.actionButton.setOnClickListener {
            orderController.setItemToDetail(item, userType == 1, position, userType == 1)
            orderController.showFragmentDetail()
        }

        viewHolder.checkBox.visibility = View.VISIBLE
        viewHolder.checkBox.setOnClickListener {
            toggleItemSelection(position)
        }

        return view
    }

    private class ViewHolder(view: View) {
        val client: TextView = view.findViewById(R.id.client)
        val seller: TextView = view.findViewById(R.id.seller)
        val date: TextView = view.findViewById(R.id.date)
        val actionButton: ImageButton = view.findViewById(R.id.actionButton)
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)
    }
}