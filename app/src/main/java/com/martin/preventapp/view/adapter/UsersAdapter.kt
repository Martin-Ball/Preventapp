package com.martin.preventapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.model.entities.UserModel
import com.martin.preventapp.view.entities.User

class UsersAdapter(
    private val context: Context,
    private val items: List<UserModel>,
    private val userActions: UsersActionInterface
): BaseAdapter()  {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): UserModel {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item = getItem(position)

        viewHolder.userName.text = item.username
        viewHolder.rol.text = item.groupName
        viewHolder.actionButton.setOnClickListener {
            userActions.showUser(item)
        }

        return view
    }

    private class ViewHolder(view: View) {
        val userName: TextView = view.findViewById(R.id.user)
        val rol: TextView = view.findViewById(R.id.rol)
        val actionButton: ImageButton = view.findViewById(R.id.actionButton)
    }
}

interface UsersActionInterface {
    fun showUser(user: UserModel)
}