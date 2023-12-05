package com.martin.preventapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.martin.preventapp.R
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.entities.ProductOrder

class ProductResumeAdapter(private val context: Context, private val productList: List<ProductOrder>) : BaseAdapter() {

    override fun getCount(): Int {
        return productList.size
    }

    override fun getItem(position: Int): ProductOrder {
        return productList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val product = getItem(position)
        viewHolder.productName.text = product.productName
        viewHolder.productPrice.text = "$${product.price.toString()}"
        viewHolder.productBrand.text = "Marca: ${product.brand}"
        viewHolder.productUnit.text = "Cantidad: ${product.amount}"

        return view
    }

    private class ViewHolder(view: View) {
        val productName: TextView = view.findViewById(R.id.name_product)
        val productPrice: TextView = view.findViewById(R.id.price_product)
        val productBrand: TextView = view.findViewById(R.id.brand_product)
        val productUnit: TextView = view.findViewById(R.id.unit_product)
    }
}