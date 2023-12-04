package com.martin.preventapp.controller.admin.lists

import android.content.Context
import com.martin.preventapp.controller.admin.interfaces.ListControllerInterface
import com.martin.preventapp.model.admin.list.ListModel
import com.martin.preventapp.model.entities.ClientListModelEntity
import com.martin.preventapp.model.entities.ListModelEntity
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.fragments.admin.list.ListFragment

class ListController : ListControllerInterface.Controller {

    @JvmField
    var context: Context? = null
    @JvmField
    var view: ListControllerInterface.View? = null

    private lateinit var listPrices: ListModelEntity
    private lateinit var listClient: ClientListModelEntity

    companion object {
        private var listController: ListController? = null
        @JvmStatic
        val instance: ListController?
            get() {
                if (listController == null) {
                    listController = ListController()
                }
                return listController
            }
    }

    override fun setView(view: ListControllerInterface.View) {
        this.view = view
    }

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun createListPrices(list: ListModelEntity) {
        ListModel.instance!!.createListPrices(list)
    }

    override fun downloadListPrices() {
        ListModel.instance!!.getListPrices()
    }

    override fun showListPrices(list: ListModelEntity) {
        listPrices = list
        ListFragment.instance!!.showList()
    }

    override fun getListPrices(): ListModelEntity {
        return listPrices
    }

    override fun createListClient(list: List<Client>) {
        ListModel.instance!!.createListClient(list)
    }

    override fun showClientList(list: ClientListModelEntity) {
        listClient = list
        ListFragment.instance!!.showListClient()
    }

    override fun downloadClientList() {
        ListModel.instance!!.getListClient()
    }

    override fun getListClient(): ClientListModelEntity {
        return listClient
    }

    override fun showToast(text: String) {
        view!!.showToast(text)
    }
}