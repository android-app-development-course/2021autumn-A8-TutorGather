package com.example.mobile_phone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobile_phone.R
import com.example.mobile_phone.adapter.OrderAdapter
import com.example.mobile_phone.bean.Order
import com.example.mobile_phone.bean.User
import com.example.mobile_phone.webData.OrderWebData
import kotlinx.android.synthetic.main.order_detail_fragement.*
import com.example.mobile_phone.enum.OrderStatus

class OrderDisplayFragment(i: Int) : Fragment() {
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
        if (orderparam == 0) {
            if (User.canBeTeacher) {
                val orderList = listOf(
                    Order(subject = "该身份权限访问！"),
                )
                val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
                orderListView.adapter = adapter

            } else {
                print(User.id)
                val orderList = OrderWebData.getOrdersByUserIdAndStatus(User.id, OrderStatus.DRAFT)
                print(orderList)
                val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
                orderListView.adapter = adapter
            }
        } else if (orderparam == 1) {
            val orderList = OrderWebData.getOrdersByUserIdAndStatus(User.id, OrderStatus.PUBLISH)
            print(orderList)
            val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
            orderListView.adapter = adapter

        }
        else {
            val orderList = OrderWebData.getOrdersByUserIdAndStatus(User.id, OrderStatus.ACCEPT)
            val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
            orderListView.adapter = adapter

        }
        orderListView.setOnItemClickListener { _, _, _, _ ->
            findNavController().navigate(R.id.action_fragment_header_to_fragment_publish)
        }
    }
}