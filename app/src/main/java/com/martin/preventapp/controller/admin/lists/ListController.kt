package com.martin.preventapp.controller.admin.lists

import android.content.Context
import com.martin.preventapp.controller.admin.interfaces.ListControllerInterface
import com.martin.preventapp.model.admin.list.ListModel
import com.martin.preventapp.model.entities.ListModelEntity

class ListController : ListControllerInterface.Controller {

    @JvmField
    var context: Context? = null
    @JvmField
    var view: ListControllerInterface.View? = null

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

    override fun showToast(text: String) {
        view!!.showToast(text)
    }
}