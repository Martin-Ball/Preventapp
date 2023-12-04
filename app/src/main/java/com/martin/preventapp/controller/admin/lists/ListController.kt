package com.martin.preventapp.controller.admin.lists

import android.content.Context
import com.martin.preventapp.controller.admin.interfaces.ListControllerInterface
import com.martin.preventapp.model.admin.list.ListModel
import com.martin.preventapp.model.entities.ListModelEntity
import com.martin.preventapp.view.fragments.admin.list.ListFragment

class ListController : ListControllerInterface.Controller {

    @JvmField
    var context: Context? = null
    @JvmField
    var view: ListControllerInterface.View? = null

    private lateinit var listPrices: ListModelEntity

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

    override fun createList(list: ListModelEntity) {
        ListModel.instance!!.createList(list)
    }

    override fun downloadList() {
        ListModel.instance!!.getList()
    }

    override fun showList(list: ListModelEntity) {
        listPrices = list
        ListFragment.instance!!.showList()
    }

    override fun getList(): ListModelEntity {
        return listPrices
    }

    override fun showToast(text: String) {
        view!!.showToast(text)
    }
}