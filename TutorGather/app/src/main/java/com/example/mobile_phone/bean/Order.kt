package com.example.mobile_phone.bean

import com.example.mobile_phone.R

data class Order(
    val id: Int=0,
    val grade: String="",
    val subject: String="",
    // 要与后端发送的名字一致
    val _abstract: String="",
    val startTime: String="",
    val endTime: String="",
    val address: String="",
    val detail: String="",
    val expense: String="",
    val status: Int=0,
    val phone: String="",
    val belongId: Int=0,
    val teacherId: Int=0,
    val imageResourceId: Int=R.mipmap.ic_launcher,
)