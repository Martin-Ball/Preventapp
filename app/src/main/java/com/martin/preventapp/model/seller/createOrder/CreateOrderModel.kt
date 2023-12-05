package com.martin.preventapp.model.seller.createOrder

import android.util.Log
import com.martin.preventapp.controller.admin.lists.ListController
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.controller.seller.interfaces.CreateOrderInterface
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.ClientListModelEntity
import com.martin.preventapp.model.entities.ListModelEntity
import com.martin.preventapp.model.entities.OrderModel
import com.martin.preventapp.model.entities.Request.CreateOrderRequest
import com.martin.preventapp.model.entities.Request.ProductOrderRequest
import com.martin.preventapp.model.entities.Response.ListClientResponse
import com.martin.preventapp.model.entities.Response.ListResponse
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.ItemAmount
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.entities.ProductOrder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateOrderModel : CreateOrderInterface.Model {

    private var itemList: MutableList<ProductOrder> = mutableListOf()
    private var clientSelected : String = ""
    private lateinit var order : OrderModel

    companion object {
        private var createOrderModel: CreateOrderModel? = null

        @JvmStatic
        val instance: CreateOrderModel?
            get() {
                if (createOrderModel == null) {
                    createOrderModel = CreateOrderModel()
                }
                return createOrderModel
            }
    }

    override fun productsOrder(listItems: List<ItemAmount>) {
        listItems.forEach { product ->
            this.itemList.add(ProductOrder(product.name, product.brand, product.presentation, product.quantityUnit, product.price, product.quantity))
        }
    }

    override fun setClientSelected(clientSelected: String) {
        this.clientSelected = clientSelected
    }

    override fun getOrder(): OrderModel {
        return OrderModel(clientSelected, itemList, "")
    }

    override fun sendOrder(order: OrderModel) {
        val apiService = Application.getApiService()

        val call = apiService.createOrder(
            Application.getTokenShared(CreateOrderController.instance!!.context!!) ?: "",
            CreateOrderRequest(
                order.client,
                Application.getUserShared(CreateOrderController.instance!!.context!!) ?: "",
                order.notes,
                order.products.map { ProductOrderRequest(it.productName, it.amount, it.price) }
            )
        )

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        CreateOrderController.instance!!.showToast("Pedido enviado!")
                        CreateOrderController.instance!!.goToMain()
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { CreateOrderController.instance!!.showToast(it) }
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
            Application.getTokenShared(CreateOrderController.instance!!.context!!) ?: "",
            Application.getUserShared(CreateOrderController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<ListClientResponse> {
            override fun onResponse(call: Call<ListClientResponse>, response: Response<ListClientResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        CreateOrderController.instance!!.showClients(
                            responseList.listClient.map { Client(
                                it.name,
                                it.address,
                                it.deliveryHour
                            ) }
                        )
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { CreateOrderController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                }
            }

            override fun onFailure(call: Call<ListClientResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
            }
        })
    }

    override fun getListProducts() {
        val apiService = Application.getApiService()

        val call = apiService.getList(
            Application.getTokenShared(CreateOrderController.instance!!.context!!) ?: "",
            Application.getUserShared(CreateOrderController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<ListResponse> {
            override fun onResponse(call: Call<ListResponse>, response: Response<ListResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        CreateOrderController.instance!!.showListPrices(
                            responseList.listProducts.map { Product(
                                it.productName,
                                it.brand,
                                it.presentation,
                                it.unit,
                                it.price
                            ) }
                        )
                    }else{
                        CreateOrderController.instance!!.showToast("Error en la respuesta")
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { ListController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                    CreateOrderController.instance!!.showToast(response.code().toString())
                }
            }

            override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
                CreateOrderController.instance!!.showToast(t.toString())
            }
        })
    }
}