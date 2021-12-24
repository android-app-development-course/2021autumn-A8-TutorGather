package com.example.mobile_phone.webData

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mobile_phone.bean.Order
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import kotlin.concurrent.thread
import com.example.mobile_phone.enum.OrderStatus
class OrderWebData {
    private val urlPrefix = "http://120.24.195.28:8080"

    private fun parseJSONWithGSON(jsonData: String): List<Order> {
        val gson = Gson()
        val typeOf = object : TypeToken<List<Order>>() {}.type
        return gson.fromJson(jsonData, typeOf)
    }

    private fun connection(url:String):String? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        return response.body?.string()
    }

    fun getRandomOrders(number: Int): List<Order> {
        // http://120.24.195.28:8080/getUser?userId=1
        val responseData = connection("$urlPrefix/getOrders?number=$number")
        if (responseData != null) {
            println("getRandomOrders: $responseData")
            return parseJSONWithGSON(responseData)
        }
        println("Not Get Random Orders")
        return listOf()
    }

    fun getOrdersByUserIdAndStatus(userId: Int, status:OrderStatus): List<Order> {
        val responseData = connection("$urlPrefix/getOrders?number=$userId&status=$status")
        if (responseData != null) {
            println("getOrdersByUserIdAndStatus: $responseData")
            return parseJSONWithGSON(responseData)
        }
        println("userId $userId, status: $status orders is None")
        return listOf()

    }

    fun getOrdersByTeacherIdAndStatus(teacherId: Int, status:OrderStatus): List<Order> {
        TODO()
    }

    fun getOrderByOrderId(orderId: Int): Order {
        val responseData = connection("$urlPrefix/getOrder?orderId=$orderId")
        if (responseData != null) {
            println("getOrdersByUserIdAndStatus: $responseData")
            return parseJSONWithGSON(responseData).first()
        }
        println("orderId: $orderId is null")
        return Order()
    }

    fun publishOrder(order: Order) {

    }

    fun acceptOrder(orderId: Int, teacherId: Int) {

    }

    fun revokeOrder(orderId: Int) {

    }

}