package com.martin.preventapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import com.martin.preventapp.R
import com.martin.preventapp.view.entities.Permission

class PermissionsAdapter(
    context: Context,
    private val resource: Int,
    private val itemsWithCheckbox: List<Permission>
) : ArrayAdapter<Permission>(context, resource, itemsWithCheckbox) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            val inflater = LayoutInflater.from(context)
            itemView = inflater.inflate(resource, parent, false)
        }

        val checkBox = itemView!!.findViewById<CheckBox>(R.id.checkBox)
        val currentItem = itemsWithCheckbox[position]

        checkBox.text = currentItem.text
        checkBox.isChecked = currentItem.isChecked

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            currentItem.isChecked = isChecked
        }

        return itemView
    }

    fun getUpdatedPermissionsList(): List<Permission> {
        return itemsWithCheckbox
    }
}