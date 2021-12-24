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
import com.example.mobile_phone.webData.OrderWebData
import kotlinx.android.synthetic.main.order_detail_fragement.*
import com.example.mobile_phone.bean.User
import com.example.mobile_phone.databinding.FragmentHeaderBinding

class OrderDisplayFragment(i: Int) :Fragment() {
    private var orderparam=i;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        if(getArguments()!=null){
//            orderparam=(getArguments()?.getInt("orderparam")!!);
//        }
        return inflater.inflate(R.layout.order_detail_fragement,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(orderparam==0){

            if(User.userstatus==true){
                val orderList= listOf(
                    Order(subject = "该身份权限访问！"),
                )
                val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
                list_view.adapter=adapter

            }else{
                print(User.userId)
                val orderList = OrderWebData().getOrdersByUserIdAndStatus(User.userId,0)
                print(orderList)
                val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
                list_view.adapter=adapter

            }
        }else if(orderparam==1){
            val orderList = OrderWebData().getOrdersByUserIdAndStatus(User.userId,1)
            print(orderList)
            val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
            list_view.adapter=adapter

        }else{
            val orderList = OrderWebData().getOrdersByUserIdAndStatus(User.userId,2)
            val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
            list_view.adapter=adapter

        }
        list_view.setOnItemClickListener { _, _, _, _ ->
            findNavController().navigate(R.id.action_fragment_header_to_fragment_publish)
        }
//        binding.buttonPublish.setOnClickListener {
//            findNavController().navigate(R.id.action_fragment_header_to_fragment_publish)
//        }

    }

//    fun thenewInstance(int: Int): OrderDisplayFragment {
//        val args = Bundle();
//        val fragment = OrderDisplayFragment(1);
//        args.putInt("orderparam",int);
//        fragment.arguments = args;
//        return fragment;
//    }
}