package com.example.mobile_phone.webData

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mobile_phone.bean.Order
import com.example.mobile_phone.bean.User
import com.example.mobile_phone.bean.User.status
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.mobile_phone.enum.OrderStatus
import okhttp3.FormBody

object OrderWebData: HttpConnect<Order>() {
    private const val urlPrefix = "http://120.24.195.28:8080"
    // 不要移动到HttpConnect中, 会因为无法识别泛型导致, gson转型出错
    private fun parseJSONWithGSON(jsonData: String): List<Order> {
        val gson = Gson()
        val typeOf = object : TypeToken<List<Order>>() {}.type
        return gson.fromJson(jsonData, typeOf)
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

    fun getOrdersByUserIdAndStatus(userId: Int, orderStatus: OrderStatus): List<Order> {
        val status = orderStatus.toInt()
        val responseData = getRequest("$urlPrefix/getOrders?userId=$userId&status=$status")
        if (responseData != null) {
            println("getOrdersByUserIdAndStatus: $responseData")
            return parseJSONWithGSON(responseData)
        }
        println("userId $userId, status: $status orders is None")
        return listOf()
    }

    fun getOrdersByTeacherIdAndStatus(teacherId: Int, orderStatus: OrderStatus): List<Order> {
        val status = orderStatus.toInt()
        val responseData = getRequest("$urlPrefix/getOrders?teacherId=$teacherId&status=$status")
        if (responseData != null) {
            println("getOrdersByUserIdAndStatus: $responseData")
            return parseJSONWithGSON(responseData)
        }
        println("userId $teacherId, status: $status orders is None")
        return listOf()
    }

    fun getOrderByOrderId(orderId: Int): Order {
        val responseData = getRequest("$urlPrefix/getOrder?orderId=$orderId")
        if (responseData != null) {
            val gson = Gson()
            return gson.fromJson(responseData, Order::class.java)
        }
        println("orderId: $orderId is null")
        return Order()
    }

    fun publishOrder(order: Order): Boolean {
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
            .add("belongId", User.id.toString())
            .build()
        val responseData = postRequest("$urlPrefix/publishOrder", formBody)
        if (responseData != null) {
            if(responseData.contains("error")){
                return false
            }
            println("publish : $responseData")
            return true
        } else {
            println("publish error, responseData is null")
            return false
        }

    }

    fun acceptOrder(orderId: Int, teacherId: Int) {
        getRequest("$urlPrefix/acceptOrder?orderId=$orderId&teacherId=$teacherId")
    }

    fun revokeOrder(orderId: Int) {
        getRequest("$urlPrefix/revokeOrder?orderId=$orderId")
    }

}