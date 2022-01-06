package com.example.mobile_phone.enum

import java.sql.Types

enum class OrderStatus(val value: Int) {
    DRAFT(0),
    PUBLISH(1),
    ACCEPT(2),
    FINISH(3);
    companion object {
        fun fromInt(value: Int) = OrderStatus.values().first { it.value == value }
    }
    fun toInt(): Int {
        return this.value
    }
}