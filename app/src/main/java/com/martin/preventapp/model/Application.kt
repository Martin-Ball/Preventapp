package com.martin.preventapp.model

import android.content.Context
import com.google.android.gms.common.api.Api
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.martin.preventapp.view.entities.Permission
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


class Application {
    companion object {
        private const val BASE_URL = "http://192.168.0.148:8000/api/"
        private const val BASE_URL_ROUTE = "https://api.openrouteservice.org/"
        private const val SHARED_NAME = "PREVENTAPP"
        private const val SHARED_TOKEN = "TOKEN"
        private const val SHARED_USER = "USER"
        private const val SHARED_GROUP = "GROUP"
        private const val SHARED_PERMISSIONS = "PERMISSIONS"
        const val API_KEY_MAPS = "5b3ce3597851110001cf62480522c04be0284a8588891a002abb3afd"

        fun getApiService(): ApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val json = Json {
                ignoreUnknownKeys = true
                isLenient = true
            }

            val contentType = "application/json".toMediaType()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(json.asConverterFactory(contentType))
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }

        fun getApiRoute(): ApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val json = Json {
                ignoreUnknownKeys = true
                isLenient = true
            }

            val contentType = "application/json".toMediaType()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_ROUTE)
                .addConverterFactory(json.asConverterFactory(contentType))
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }

        fun saveTokenShared(context: Context, value: String) {
            val sharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(SHARED_TOKEN, value)
            editor.apply()
        }

        fun getTokenShared(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString(SHARED_TOKEN, null)
        }

        fun clearTokenShared(context: Context) {
            val sharedPreferences = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove(SHARED_TOKEN)
            editor.apply()

            clearUserShared(context)
            clearGroupUserShared(context)
            clearPermissionsShared(context)
        }

        fun saveUserShared(context: Context, value: String) {
            val sharedPreferences = context.getSharedPreferences(SHARED_USER, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(SHARED_USER, value)
            editor.apply()
        }

        fun getUserShared(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences(SHARED_USER, Context.MODE_PRIVATE)
            return sharedPreferences.getString(SHARED_USER, null)
        }

        fun clearUserShared(context: Context) {
            val sharedPreferences = context.getSharedPreferences(SHARED_USER, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove(SHARED_USER)
            editor.apply()
        }

        fun saveGroupUserShared(context: Context, value: String) {
            val sharedPreferences = context.getSharedPreferences(SHARED_GROUP, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(SHARED_GROUP, value)
            editor.apply()
        }

        fun getGroupUserShared(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences(SHARED_GROUP, Context.MODE_PRIVATE)
            return sharedPreferences.getString(SHARED_GROUP, null)
        }

        fun clearGroupUserShared(context: Context) {
            val sharedPreferences = context.getSharedPreferences(SHARED_GROUP, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove(SHARED_GROUP)
            editor.apply()
        }
        fun savePermissionsUserShared(context: Context, permissionsList: List<Permission>) {
            val gson = Gson()
            val permissionsJson = gson.toJson(permissionsList)

            val sharedPreferences = context.getSharedPreferences(SHARED_PERMISSIONS, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(SHARED_PERMISSIONS, permissionsJson)
            editor.apply()
        }

        fun getPermissionsUserShared(context: Context): List<Permission>? {
            val sharedPreferences = context.getSharedPreferences(SHARED_PERMISSIONS, Context.MODE_PRIVATE)
            val permissionsJson = sharedPreferences.getString(SHARED_PERMISSIONS, null)

            if (permissionsJson != null) {
                val type = object : TypeToken<List<Permission>>() {}.type
                return Gson().fromJson(permissionsJson, type)
            }

            return null
        }

        fun clearPermissionsShared(context: Context) {
            val sharedPreferences = context.getSharedPreferences(SHARED_PERMISSIONS, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove(SHARED_PERMISSIONS)
            editor.apply()
        }


    }
}