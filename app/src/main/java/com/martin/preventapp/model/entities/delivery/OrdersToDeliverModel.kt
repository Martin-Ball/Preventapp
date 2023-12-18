package com.martin.preventapp.model.entities.delivery

import android.util.Log
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.controller.delivery.interfaces.OrdersToDeliverInterface
import com.martin.preventapp.controller.delivery.orders.OrdersToDeliverController
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Response.NewOrdersResponse
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.Product
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersToDeliverModel: OrdersToDeliverInterface.Model {
    companion object {
        private var ordersToDeliver: OrdersToDeliverModel? = null
        @JvmStatic
        val instance: OrdersToDeliverModel?
            get() {
                if (ordersToDeliver == null) {
                    ordersToDeliver = OrdersToDeliverModel()
                }
                return ordersToDeliver
            }
    }

    override fun getNewOrders(isAdmin: Boolean) {
        val apiService = Application.getApiService()

        val call = apiService.getNewOrders(
            Application.getTokenShared(OrdersToDeliverController.instance!!.context!!) ?: "",
            Application.getUserShared(OrdersToDeliverController.instance!!.context!!) ?: "",
            isAdmin
        )

        call.enqueue(object : Callback<List<NewOrdersResponse>> {
            override fun onResponse(call: Call<List<NewOrdersResponse>>, response: Response<List<NewOrdersResponse>>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        OrdersToDeliverController.instance!!.showOrdersList(
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
                    response.errorBody()?.string()?.let { OrdersToDeliverController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<NewOrdersResponse>>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun notDeliverOrder(id: Int) {
        val apiService = Application.getApiService()

        val call = apiService.notDeliverOrder(
            Application.getTokenShared(OrdersToDeliverController.instance!!.context!!) ?: "",
            id,
            Application.getUserShared(NewOrdersController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        OrdersToDeliverController.instance!!.showToast("Pedido cancelado")
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { OrdersToDeliverController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun orderDelivered(id: Int) {
        val apiService = Application.getApiService()

        val call = apiService.orderDelivered(
            Application.getTokenShared(OrdersToDeliverController.instance!!.context!!) ?: "",
            id,
            Application.getUserShared(NewOrdersController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        OrdersToDeliverController.instance!!.showToast("Pedido entregado!")
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { OrdersToDeliverController.instance!!.showToast(it) }
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