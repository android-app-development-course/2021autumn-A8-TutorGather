package com.example.mobile_phone.bean

import com.example.mobile_phone.enum.UserStatus
// 单例类, login后会将信息同步
object User{
    var id: Int = 0
    var name: String = ""
    var phone: String = ""
    var password: String = ""
    var status: UserStatus = UserStatus.PARENT
    var address: String = ""
    var canBeTeacher: Boolean = false


}
