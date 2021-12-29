package com.example.mobile_phone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_phone.R
import com.example.mobile_phone.adapter.OrderAdapter
import com.example.mobile_phone.bean.Order
import com.example.mobile_phone.bean.User
import com.example.mobile_phone.webData.OrderWebData
import kotlinx.android.synthetic.main.order_detail_fragement.*
import com.example.mobile_phone.enum.OrderStatus
import kotlinx.android.synthetic.main.fragment_header.*
import kotlinx.android.synthetic.main.fragment_orderdetail.*
import kotlinx.coroutines.newFixedThreadPoolContext

class OrderDisplayFragment(i: OrderStatus) : Fragment() {
    private var orderparam = i;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_detail_fragement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (orderparam == OrderStatus.PUBLISH) {
            if (User.canBeTeacher) {
                val orderList = listOf(
                    Order(subject = "该身份权限访问！"),
                )
                val adapter = OrderAdapter(orderList, this)
                orderMangerListView.layoutManager = LinearLayoutManager(this.requireContext())
                //recyclerViewOrderDetail.adapter=adapter
                orderMangerListView.adapter = adapter
            }
            else {
                print(User.id)
                val orderList = OrderWebData.getOrdersByUserIdAndStatus(User.id, OrderStatus.PUBLISH)
                print(orderList)
                val adapter = OrderAdapter(orderList, this)
                orderMangerListView.layoutManager = LinearLayoutManager(this.requireContext())
                //recyclerViewOrderDetail.adapter=adapter
                orderMangerListView.adapter = adapter
            }
        }
        else if (orderparam == OrderStatus.ACCEPT) {
            val orderList = OrderWebData.getOrdersByUserIdAndStatus(User.id, OrderStatus.ACCEPT)
            print(orderList)
            val adapter = OrderAdapter(orderList, this)
            orderMangerListView.layoutManager = LinearLayoutManager(this.requireContext())
            orderMangerListView.adapter = adapter

        }
        else {
            val orderList = OrderWebData.getOrdersByUserIdAndStatus(User.id, OrderStatus.FINISH)
            if(orderList.isEmpty())
                return
            val adapter = OrderAdapter(orderList, this)
            orderMangerListView.layoutManager = LinearLayoutManager(this.requireContext())
            orderListView.adapter = adapter

        }
    }

}

