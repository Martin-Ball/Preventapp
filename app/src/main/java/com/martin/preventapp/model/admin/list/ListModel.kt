package com.martin.preventapp.model.admin.list

import android.util.Log
import com.martin.preventapp.controller.admin.interfaces.ListControllerInterface
import com.martin.preventapp.controller.admin.lists.ListController
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.ListModelEntity
import com.martin.preventapp.model.entities.Request.CreateListRequest
import com.martin.preventapp.model.entities.Request.DeleteUserRequest
import com.martin.preventapp.model.entities.Request.ProductRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListModel : ListControllerInterface.Model {

    companion object {
        private var listModel: ListModel? = null
        @JvmStatic
        val instance: ListModel?
            get() {
                if (listModel == null) {
                    listModel = ListModel()
                }
                return listModel
            }
    }

    override fun createList(list: ListModelEntity) {
        val apiService = Application.getApiService()

        val call = apiService.createList(
            Application.getTokenShared(ListController.instance!!.context!!) ?: "",
            CreateListRequest(
                list.listName,
                Application.getUserShared(ListController.instance!!.context!!) ?: "",
                list.listProducts.map{
                    ProductRequest(it.productName, it.brand, it.presentation, it.unit, it.price)
                })
        )

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        ListController.instance!!.showToast("Lista creada correctamente")
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { UserManagerController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }
}