package com.martin.preventapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.martin.preventapp.R
import com.martin.preventapp.view.entities.ClientPurchaseAudit

class ClientPurchaseAuditAdapter(private val context: Context, private val purchases: List<ClientPurchaseAudit>) : BaseAdapter() {

    private class ViewHolder {
        var fechaTextView: TextView? = null
        var preventistaEmailTextView: TextView? = null
        var montoTotalTextView: TextView? = null
        var nombreClienteTextView: TextView? = null
    }

    override fun getCount(): Int {
        return purchases.size
    }

    override fun getItem(position: Int): Any {
        return purchases[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var rowView = convertView
        val holder: ViewHolder

        if (rowView == null) {
            val inflater = LayoutInflater.from(context)
            rowView = inflater.inflate(R.layout.client_purchase_audit_item, null)

            holder = ViewHolder()
            holder.fechaTextView = rowView.findViewById(R.id.date_textview)
            holder.preventistaEmailTextView = rowView.findViewById(R.id.seller_textview)
            holder.montoTotalTextView = rowView.findViewById(R.id.total_amount_textview)
            holder.nombreClienteTextView = rowView.findViewById(R.id.client_textview)

            rowView.tag = holder
        } else {
            holder = rowView.tag as ViewHolder
        }

        val purchase = getItem(position) as ClientPurchaseAudit

        holder.fechaTextView?.text = purchase.date
        holder.preventistaEmailTextView?.text = purchase.sellerEmail
        holder.montoTotalTextView?.text = "Monto comprado: $ ${purchase.totalAmount}"
        holder.nombreClienteTextView?.text = purchase.clientName

        return rowView!!
    }
}