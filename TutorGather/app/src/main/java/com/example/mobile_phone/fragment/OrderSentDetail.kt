package com.example.mobile_phone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobile_phone.R
import com.example.mobile_phone.adapter.OrderAdapter
import com.example.mobile_phone.bean.Order
import kotlinx.android.synthetic.main.order_completedetail_fragment.*

class OrderSentDetail:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_sentdetail_fragement,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderList = listOf(
            Order("小红", "编程老师速来，要求男，姓陈", "一年级", R.mipmap.ic_launcher),
            Order("小明", "编程老师速来", "一年级", R.mipmap.ic_launcher)
        )

        val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
        list_view.adapter=adapter
    }
}