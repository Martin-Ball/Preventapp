package com.martin.preventapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.martin.preventapp.R
import com.martin.preventapp.model.entities.Response.RecommendedReport

class ReportsAuditAdapter(private val context: Context, private val reports: List<RecommendedReport>) : BaseAdapter() {

    override fun getCount(): Int {
        return reports.size
    }

    override fun getItem(position: Int): Any {
        return reports[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_report_audit, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val report = getItem(position) as RecommendedReport
        viewHolder.bind(report)

        return view
    }

    private class ViewHolder(view: View) {
        private val dateTextView: TextView = view.findViewById(R.id.date_textview)
        private val clientNameTextView: TextView = view.findViewById(R.id.client_name_textview)
        private val addressTextView: TextView = view.findViewById(R.id.address_textview)

        fun bind(report: RecommendedReport) {
            dateTextView.text = report.date
            clientNameTextView.text = report.clientName
            addressTextView.text = report.addressClient
        }
    }
}