package com.example.mobile_phone.webData

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

open class HttpConnect<T> {
    private val urlPrefix = "http://120.24.195.28:8080"

    fun getRequest(url: String): String? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        return response.body?.string()
    }

    fun postRequest(url: String, postBody: FormBody): String? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .post(postBody)
            .build()
        val response = client.newCall(request).execute()
        return response.body?.string()
    }
}