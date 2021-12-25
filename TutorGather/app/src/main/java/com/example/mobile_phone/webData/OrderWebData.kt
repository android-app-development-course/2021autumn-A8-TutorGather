package com.example.mobile_phone.webData

import com.example.mobile_phone.bean.Order
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import com.example.mobile_phone.enum.OrderStatus
import okhttp3.FormBody

class OrderWebData {
    private val urlPrefix = "http://120.24.195.28:8080"

    private fun parseJSONWithGSON(jsonData: String): List<Order> {
        val gson = Gson()
        val typeOf = object : TypeToken<List<Order>>() {}.type
        return gson.fromJson(jsonData, typeOf)
    }

    private fun getRequest(url:String):String? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        return response.body?.string()
    }

    private fun postRequest(url:String, postBody: FormBody):String? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .post(postBody)
            .build()
        val response = client.newCall(request).execute()
        return response.body?.string()
    }

    fun getRandomOrders(number: Int): List<Order> {
        val responseData = getRequest("$urlPrefix/getOrders?number=$number")
        if (responseData != null) {
            println("getRandomOrders: $responseData")
            return parseJSONWithGSON(responseData)
        }
        println("Not Get Random Orders")
        return listOf()
    }

<<<<<<< HEAD
    fun getOrdersByUserIdAndStatus(userId: Int, status:OrderStatus): List<Order> {
        val responseData = getRequest("$urlPrefix/getOrders?number=$userId&status=$status")
=======
    fun getOrdersByUserIdAndStatus(userId: Int, status: Int): List<Order> {
        val responseData = connection("$urlPrefix/getOrders?userId=$userId&status=$status")
>>>>>>> c03b6983e250f4bcc862cf64babc03330151d19a
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
        val responseData = getRequest("$urlPrefix/getOrder?orderId=$orderId")
        if (responseData != null) {
//            Log.i(TAG, "getOrderById: $responseData, data: $responseData, \norderId$orderId")
            val gson = Gson()
            return gson.fromJson(responseData, Order::class.java)
        }
        println("orderId: $orderId is null")
        return Order()
    }

    fun publishOrder(order: Order):Boolean {
        println(order)
        val formBody = FormBody.Builder()
            .add("subject", order.subject)
            .add("grade", order.grade)
            .add("abstract", order._abstract)
            .add("startTime", order.startTime)
            .add("endTime", order.endTime)
            .add("address", order.address)
            .add("detail", order.detail)
            .add("expense", order.expense)
            .add("phone", order.phone)
            .add("belongId", order.belongId.toString())
            .build()
        val responseData = postRequest("$urlPrefix/publishOrder",formBody)
        if(responseData != null) {
            println("publish : $responseData")
            return true
        }
        else {
            println("publish error, responseData is null")
            return false
        }

    }

    fun acceptOrder(orderId: Int, teacherId: Int) {

    }

    fun revokeOrder(orderId: Int) {

    }

}