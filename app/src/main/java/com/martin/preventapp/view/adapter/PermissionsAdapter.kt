package com.martin.preventapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import com.martin.preventapp.R
import com.martin.preventapp.model.entities.Request.PermissionModel

class PermissionsAdapter(
    context: Context,
    private val resource: Int,
    private val itemsWithCheckbox: List<PermissionModel>
) : ArrayAdapter<PermissionModel>(context, resource, itemsWithCheckbox) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            val inflater = LayoutInflater.from(context)
            itemView = inflater.inflate(resource, parent, false)
        }

        val checkBox = itemView!!.findViewById<CheckBox>(R.id.checkBox)
        val currentItem = itemsWithCheckbox[position]

        checkBox.text = currentItem.permissionName
        checkBox.isChecked = currentItem.state == 1

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            currentItem.state = if(isChecked) 1 else 0
        }

        return itemView
    }

    fun getUpdatedPermissionsList(): List<PermissionModel> {
        return itemsWithCheckbox
    }
}