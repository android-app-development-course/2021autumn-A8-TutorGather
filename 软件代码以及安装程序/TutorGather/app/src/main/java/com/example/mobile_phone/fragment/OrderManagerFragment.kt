package com.example.mobile_phone.fragment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_phone.R
import com.example.mobile_phone.adapter.OrderAdapter
import com.example.mobile_phone.bean.Order
import com.example.mobile_phone.bean.User
import com.example.mobile_phone.enum.OrderStatus
import com.example.mobile_phone.enum.UserStatus
import com.example.mobile_phone.webData.OrderWebData
import kotlinx.android.synthetic.main.fragment_order_manager.*

class OrderManagerFragment:Fragment() {
    private lateinit var orderList:ArrayList<Order>
    private lateinit var adapter: OrderAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_manager, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        Sentbutton.setOnClickListener{
            replaceStatus(OrderStatus.PUBLISH)
        }
        Acceptbutton.setOnClickListener{
            replaceStatus(OrderStatus.ACCEPT)
        }
        Completedbutton.setOnClickListener{
            replaceStatus(OrderStatus.FINISH)
        }
        if(User.status == UserStatus.PARENT)
            orderList = ArrayList(OrderWebData.getOrdersByUserIdAndStatus(User.id, OrderStatus.PUBLISH))
        else
            orderList = ArrayList(OrderWebData.getOrdersByTeacherIdAndStatus(User.id, OrderStatus.PUBLISH))
        adapter = OrderAdapter(orderList, this)
        orderMangerListView.layoutManager = LinearLayoutManager(this.requireContext())
        orderMangerListView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun replaceStatus(status: OrderStatus) {
        orderList.clear()
        if(User.status == UserStatus.PARENT) {
            when (status) {
                // 先清空, 再add
                OrderStatus.ACCEPT -> putIntoOrderList(OrderWebData.getOrdersByUserIdAndStatus(User.id, OrderStatus.ACCEPT))
                OrderStatus.PUBLISH -> putIntoOrderList(OrderWebData.getOrdersByUserIdAndStatus(User.id, OrderStatus.PUBLISH))
                OrderStatus.FINISH -> putIntoOrderList(OrderWebData.getOrdersByUserIdAndStatus(User.id, OrderStatus.FINISH))
                else -> Log.e(TAG, "replaceStatus: error status not exist",)
            }
        }
        // 后端接口还没写
        else {
            when (status) {
                OrderStatus.ACCEPT -> putIntoOrderList(OrderWebData.getOrdersByTeacherIdAndStatus(User.id, OrderStatus.ACCEPT))
                OrderStatus.PUBLISH -> putIntoOrderList(OrderWebData.getOrdersByTeacherIdAndStatus(User.id, OrderStatus.PUBLISH))
                OrderStatus.FINISH -> putIntoOrderList(OrderWebData.getOrdersByTeacherIdAndStatus(User.id, OrderStatus.FINISH))
                else -> Log.e(TAG, "replaceStatus: error status not exist",)
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun putIntoOrderList(list: List<Order>) {
        for(order in list){
            orderList.add(order)
        }
    }
}

