package com.martin.preventapp.model.admin.list

import android.util.Log
import com.martin.preventapp.controller.admin.interfaces.ListControllerInterface
import com.martin.preventapp.controller.admin.lists.ListController
import com.martin.preventapp.controller.admin.users.UserManagerController
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.ClientListModelEntity
import com.martin.preventapp.model.entities.ListModelEntity
import com.martin.preventapp.model.entities.Request.ClientRequest
import com.martin.preventapp.model.entities.Request.CreateClientListRequest
import com.martin.preventapp.model.entities.Request.CreateListRequest
import com.martin.preventapp.model.entities.Request.ProductRequest
import com.martin.preventapp.model.entities.Response.ListClientResponse
import com.martin.preventapp.model.entities.Response.ListResponse
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.Product
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

    override fun createListPrices(list: ListModelEntity) {
        val apiService = Application.getApiService()

        val call = apiService.createList(
            Application.getTokenShared(ListController.instance!!.context!!) ?: "",
            CreateListRequest(
                list.listName,
                Application.getUserShared(ListController.instance!!.context!!) ?: "",
                list.listProducts.map{
                    ProductRequest(it.productName, it.brand, it.presentation, it.unit, it.price)
                },
                list.dateValidity
            )
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

    override fun getListPrices() {
        val apiService = Application.getApiService()

        val call = apiService.getList(
            Application.getTokenShared(ListController.instance!!.context!!) ?: "",
            Application.getUserShared(ListController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<ListResponse> {
            override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        ListController.instance!!.showListPrices(
                            ListModelEntity(
                                responseList.listName,
                                responseList.listProducts.map { Product(
                                    it.productName,
                                    it.brand,
                                    it.presentation,
                                    it.unit,
                                    it.price
                                ) },
                                responseList.validityDate)
                        )
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { UserManagerController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun createListClient(list: List<Client>) {
        val apiService = Application.getApiService()

        val call = apiService.createListClient(
            Application.getTokenShared(ListController.instance!!.context!!) ?: "",
            CreateClientListRequest(
                Application.getUserShared(ListController.instance!!.context!!) ?: "",
                list.map { ClientRequest(it.name, it.address, it.deliveryHour) }
            )
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

    override fun getListClient() {
        val apiService = Application.getApiService()

        val call = apiService.getListClient(
            Application.getTokenShared(ListController.instance!!.context!!) ?: "",
            Application.getUserShared(ListController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<ListClientResponse> {
            override fun onResponse(call: Call<ListClientResponse>, response: Response<ListClientResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        ListController.instance!!.showClientList(
                            ClientListModelEntity(
                                responseList.username,
                                responseList.listClient.map { Client(
                                    it.name,
                                    it.address,
                                    it.deliveryHour
                                ) }
                            )
                        )
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { UserManagerController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ListClientResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }
}