package com.martin.preventapp.model.seller.recommended

import android.util.Log
import com.martin.preventapp.controller.admin.lists.ListController
import com.martin.preventapp.controller.seller.createOrder.CreateOrderController
import com.martin.preventapp.controller.seller.interfaces.RecommendedInterface
import com.martin.preventapp.controller.seller.recommended.RecommendedController
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.ClientListModelEntity
import com.martin.preventapp.model.entities.Response.ListClientResponse
import com.martin.preventapp.model.entities.Response.ListResponse
import com.martin.preventapp.model.entities.Response.RecommendedResponse
import com.martin.preventapp.view.entities.Client
import com.martin.preventapp.view.entities.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendedModel : RecommendedInterface.Model {

    private lateinit var clientSelected: String

    companion object {
        private var recommendedModel: RecommendedModel? = null
        @JvmStatic
        val instance: RecommendedModel?
            get() {
                if (recommendedModel == null) {
                    recommendedModel = RecommendedModel()
                }
                return recommendedModel
            }
    }

    override fun getRecommendedProducts(clientSelected: String) {
        val apiService = Application.getApiService()

        val call = apiService.getRecommendedProducts(
            Application.getTokenShared(RecommendedController.instance!!.context!!) ?: "",
            clientSelected,
            Application.getUserShared(RecommendedController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<RecommendedResponse> {
            override fun onResponse(call: Call<RecommendedResponse>, response: Response<RecommendedResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        RecommendedController.instance!!.showRecommendedProducts(responseList)
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { RecommendedController.instance!!.showToast(it) }
                } else{
                    Log.e("Login error: ", response.code().toString())
                    RecommendedController.instance!!.showToast(response.code().toString())
                }
            }

            override fun onFailure(call: Call<RecommendedResponse>, t: Throwable) {
                Log.e("Login error: ", t.toString())
                RecommendedController.instance!!.showToast(t.toString())
            }
        })
    }

    override fun getClientList() {
        val apiService = Application.getApiService()

        val call = apiService.getListClient(
            Application.getTokenShared(RecommendedController.instance!!.context!!) ?: "",
            Application.getUserShared(RecommendedController.instance!!.context!!) ?: ""
        )

        call.enqueue(object : Callback<ListClientResponse> {
            override fun onResponse(call: Call<ListClientResponse>, response: Response<ListClientResponse>) {
                if (response.isSuccessful) {
                    val responseList = response.body()
                    if (responseList != null) {
                        RecommendedController.instance!!.showClientList(
                            responseList.listClient.map { Client(
                                it.name,
                                it.address,
                                it.deliveryHour
                            ) }
                        )
                    }
                } else if (response.code() == 400){
                    response.errorBody()?.string()?.let { RecommendedController.instance!!.showToast(it) }
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