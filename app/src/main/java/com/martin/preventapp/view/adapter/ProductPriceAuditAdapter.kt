package com.martin.preventapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.martin.preventapp.R
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.entities.ProductPriceAudit
import java.text.SimpleDateFormat
import java.util.*

class ProductPriceAuditAdapter(private val context: Context, private val productList: List<ProductPriceAudit>) : BaseAdapter() {

    override fun getCount(): Int {
        return productList.size
    }

    override fun getItem(position: Int): Any {
        return productList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView = convertView
        val viewHolder: ViewHolder

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.product_price_audit, parent, false)
            viewHolder = ViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            viewHolder = itemView.tag as ViewHolder
        }

        val product = getItem(position) as ProductPriceAudit

        viewHolder.listName.text = product.productName
        viewHolder.unitPrice.text = "$ ${product.unitPrice}"
        viewHolder.validityDate.text = product.validityDate
        viewHolder.creationDate.text = formatDate(product.creationDate)

        return itemView!!
    }

    private fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        return outputFormat.format(date!!)
    }

    private class ViewHolder(view: View) {
        val listName: TextView = view.findViewById(R.id.list_name_textview)
        val unitPrice: TextView = view.findViewById(R.id.unit_price_textview)
        val validityDate: TextView = view.findViewById(R.id.validity_date_textview)
        val creationDate: TextView = view.findViewById(R.id.creation_date_textview)
    }
}