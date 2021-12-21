package com.example.mobile_phone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobile_phone.R
import com.example.mobile_phone.adapter.OrderAdapter
import com.example.mobile_phone.bean.Order
import com.example.mobile_phone.databinding.FragmentHeaderBinding
import kotlinx.android.synthetic.main.order_completedetail_fragment.*

class OrderCompleteDetail:Fragment() {
    private var _binding: FragmentHeaderBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_completedetail_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderList = listOf(
            Order("小蓝", "编程老师速来", "一年级", R.mipmap.ic_launcher),
            Order("小紫", "编程老师速来", "一年级", R.mipmap.ic_launcher)
        )

        val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
        list_view.adapter=adapter
    }
}