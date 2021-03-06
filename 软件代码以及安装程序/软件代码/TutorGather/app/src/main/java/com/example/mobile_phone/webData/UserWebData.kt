package com.example.mobile_phone.webData

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mobile_phone.bean.User
import okhttp3.FormBody
import org.json.JSONObject

object UserWebData: HttpConnect<User>() {
    private const val urlPrefix = "http://120.24.195.28:8080"
    fun register(phone:String, password:String, name:String="name is null",address:String="address is null"):Boolean {
        val formBody = FormBody.Builder()
            .add("name", name)
            .add("phone", phone)
            .add("password", password)
            .add("address", address)
            .build()
        val responseData = postRequest("$urlPrefix/insertUser", formBody)
        return if (responseData == "phone has been register") {
            Log.e(TAG, "phone has been register")
            false
        } else {
            Log.e(TAG, "register: $phone")
            true
        }
    }
    fun getUserJSONFromUserId(userId:Int):JSONObject {
        val responseData = getRequest("$urlPrefix/getUser?userId=${userId}")
        if(responseData == null || responseData.contains("error")){
            Log.e(TAG, "getUserNameFromUserId: network error or $userId is not exist")
        }
        return JSONObject(responseData!!)
    }

    fun login(phone:String, password:String):Boolean {
        val responseData = getRequest("$urlPrefix/getUser?phone=${phone}")
        Log.i(TAG, "login: $responseData")
        if(responseData == null){
            return false
        }
        val data = JSONObject(responseData)
        if(password == data.get("password")) {
            User.id = data.get("id") as Int
            User.name = data.get("name") as String
            User.phone = data.get("phone") as String
            User.canBeTeacher = data.get("canBeTeacher") as Boolean
            User.address = data.get("address") as String
            return true
        }
        else {
            return false
        }
    }





}