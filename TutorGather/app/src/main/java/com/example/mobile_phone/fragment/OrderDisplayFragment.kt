package com.example.mobile_phone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.mobile_phone.R
import com.example.mobile_phone.adapter.OrderAdapter
import com.example.mobile_phone.bean.Order


class OrderDisplayFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_detail_fragement,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderList = listOf<Order>()
        val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
        val listView = view.findViewById<ListView>(R.id.orderListView)
        listView.adapter=adapter
    }
}