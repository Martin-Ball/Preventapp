package com.martin.preventapp.controller.admin.interfaces

import android.content.Context
import com.martin.preventapp.model.entities.ListModelEntity

interface ListControllerInterface {
    interface View {
        fun showToast(text: String)
    }

    interface Controller {
        fun setView(view: View)
        fun setContext(context: Context)
        fun createList(list: ListModelEntity)
        fun showToast(text: String)
        fun downloadList()
        fun showList(list: ListModelEntity)
        fun getList(): ListModelEntity
    }

    interface Model {
        fun createList(list: ListModelEntity)
        fun getList()
    }
}