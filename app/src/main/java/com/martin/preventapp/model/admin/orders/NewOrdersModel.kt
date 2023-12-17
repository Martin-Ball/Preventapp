package com.martin.preventapp.model.admin.orders

import android.util.Log
import com.martin.preventapp.controller.admin.interfaces.ConfirmedOrderInterface
import com.martin.preventapp.controller.admin.interfaces.NewOrderInterface
import com.martin.preventapp.controller.admin.orders.ConfirmedOrdersController
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Response.NewOrdersResponse
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.fragments.admin.neworders.ConfirmedOrdersFragment
import com.martin.preventapp.view.fragments.delivery.OrdersDeliveredFragment
import com.martin.preventapp.view.fragments.seller.orders.OrdersFragment
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewOrdersModel : NewOrderInterface.Model, ConfirmedOrderInterface.Model {

    companion object {
        private var newOrderModel: NewOrdersModel? = null
        @JvmStatic
        val instance: NewOrdersModel?
            get() {
                if (newOrderModel == null) {
                    newOrderModel = NewOrdersModel()
                }
                return newOrderModel
            }
    }

    override fun getNewOrders(isAdmin: Boolean) {
        val apiService = Application.getApiService()

        val call = apiService.getNewOrders(
            Application.getTokenShared(NewOrdersController.instance!!.context!!) ?: "",
            Application.getUserShared(NewOrdersController.instance!!.context!!) ?: "",
            isAdmin
        )

        call.enqueue(object : Callback<List<NewOrdersResponse>> {
            override fun onResponse(call: Call<List<NewOrdersResponse>>, response: Response<List<NewOrdersResponse>>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        NewOrdersController.instance!!.showOrdersList(
                            responseList.map {
                                NewOrder(
                                    it.idOrder,
                                    it.state ?: "",
                                    it.date,
                                    it.products.map { product ->
                                        Product(
                                            product.productName,
                                            product.brand,
                                            product.presentation,
                                            product.unit,
                                            product.price,
                                            product.amount
                                        )
                                    },
                                    Client(
                                        it.client.name,
                                        it.client.address,
                                        it.client.deliveryHour
                                    ),
                                    it.sellerName,
                                    it.note
                                )
                            }
                        )
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { NewOrdersController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<NewOrdersResponse>>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun confirmOrder(idOrder: Int) {
        val apiService = Application.getApiService()

        val call = apiService.sendOrderToDelivery(
            Application.getTokenShared(NewOrdersController.instance!!.context!!) ?: "",
            idOrder,
            Application.getUserShared(NewOrdersController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        NewOrdersController.instance!!.showToast("Pedido enviado!")
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { NewOrdersController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun cancelOrder(id: Int) {
        val apiService = Application.getApiService()

        val call = apiService.cancelOrder(
            Application.getTokenShared(NewOrdersController.instance!!.context!!) ?: "",
            id,
            Application.getUserShared(NewOrdersController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        NewOrdersController.instance!!.showToast("Pedido cancelado")
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { NewOrdersController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun getOrdersByDate(date: String, groupType: Int) {
        val apiService = Application.getApiService()

        val context = when(groupType){
            1 -> ConfirmedOrdersFragment.instance!!.context
            2 -> OrdersDeliveredFragment.instance!!.context
            3 -> OrdersFragment.instance!!.context
            else -> return
        }

        val call = apiService.getOrdersByDate(
            Application.getTokenShared(context!!) ?: "",
            Application.getUserShared(context) ?: "",
            date,
            groupType
        )

        call.enqueue(object : Callback<List<NewOrdersResponse>> {
            override fun onResponse(call: Call<List<NewOrdersResponse>>, response: Response<List<NewOrdersResponse>>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        val listOrders = responseList.map {
                            NewOrder(
                                it.idOrder,
                                it.state ?: "",
                                it.date,
                                it.products.map { product ->
                                    Product(
                                        product.productName,
                                        product.brand,
                                        product.presentation,
                                        product.unit,
                                        product.price,
                                        product.amount
                                    )
                                },
                                Client(
                                    it.client.name,
                                    it.client.address,
                                    it.client.deliveryHour
                                ),
                                it.sellerName,
                                it.note
                            )
                        }

                        ConfirmedOrdersController.instance!!.showOrdersByDate(listOrders, groupType)

                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let {
                        ConfirmedOrdersController.instance!!.showToast(it, groupType)
                    }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<NewOrdersResponse>>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }
}