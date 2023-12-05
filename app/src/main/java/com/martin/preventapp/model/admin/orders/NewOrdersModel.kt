package com.martin.preventapp.model.admin.orders

import android.util.Log
import com.martin.preventapp.controller.admin.interfaces.NewOrderInterface
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Response.ListClientResponse
import com.martin.preventapp.model.entities.Response.NewOrdersResponse
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.NewOrder
import com.martin.preventapp.view.entities.OrderItem
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.entities.ProductOrder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewOrdersModel : NewOrderInterface.Model {

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

    override fun getNewOrders() {
        val apiService = Application.getApiService()

        val call = apiService.getNewOrders(
            Application.getTokenShared(NewOrdersController.instance!!.context!!) ?: "",
            Application.getUserShared(NewOrdersController.instance!!.context!!) ?: ""
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
            idOrder
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
            id
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
}