package com.martin.preventapp.model

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class Application {
    companion object {
        private const val BASE_URL = "http://192.168.0.148:8000/api/"
        private const val SHARED_NAME = "PREVENTAPP"
        private const val SHARED_TOKEN = "TOKEN"
        private const val SHARED_USER = "USER"
        private const val SHARED_GROUP = "GROUP"

        fun getApiService(): ApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                isLenient = true
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
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
    }
}