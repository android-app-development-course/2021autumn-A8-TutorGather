package com.example.mobile_phone.bean

class SingleMessage(val content: String,val type :Int) {
//    val messageText = ""
//    val time = ""
//    val contacts = ""
//    val sendToMe = true
    companion object{
        const val TYPE_RECEIVED = 0
        const val TYPE_SENT = 1
    }
}