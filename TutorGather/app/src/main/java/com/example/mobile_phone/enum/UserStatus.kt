package com.example.mobile_phone.enum

enum class UserStatus(val value: Int)  {
    TEACHER(2),
    PARENT(1);

    override fun toString(): String {
        return when(this.value) {
            1 -> "家长"
            else -> "老师"
        }
    }
}