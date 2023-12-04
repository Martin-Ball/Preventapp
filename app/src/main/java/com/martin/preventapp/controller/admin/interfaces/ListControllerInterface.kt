package com.martin.preventapp.controller.admin.interfaces

import android.content.Context
import com.martin.preventapp.model.entities.ClientListModelEntity
import com.martin.preventapp.model.entities.ListModelEntity
import com.martin.preventapp.view.entities.Client

interface ListControllerInterface {
    interface View {
        fun showToast(text: String)
    }

    interface Controller {
        fun setView(view: View)
        fun setContext(context: Context)
        fun showToast(text: String)
        fun createListPrices(list: ListModelEntity)
        fun downloadListPrices()
        fun showListPrices(list: ListModelEntity)
        fun getListPrices(): ListModelEntity

        fun createListClient(list: List<Client>)
        fun showClientList(list: ClientListModelEntity)
        fun downloadClientList()
        fun getListClient() : ClientListModelEntity
    }

    interface Model {
        fun createListPrices(list: ListModelEntity)
        fun getListPrices()
        fun createListClient(list: List<Client>)
        fun getListClient()
    }
}