package com.example.mobile_phone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobile_phone.R
import com.example.mobile_phone.enum.OrderStatus
import kotlinx.android.synthetic.main.order_select_fragment.*

class OrderManagerFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Sentbutton.setOnClickListener{
//            print(123)
            replaceFragment(OrderDisplayFragment(OrderStatus.PUBLISH))
        }
        Acceptbutton.setOnClickListener{
//            print(123545465)
            replaceFragment(OrderDisplayFragment(OrderStatus.ACCEPT))
        }
        Completedbutton.setOnClickListener{
            print(1235465)
            replaceFragment(OrderDisplayFragment(OrderStatus.FINISH))
        }
        replaceFragment(OrderDisplayFragment(OrderStatus.PUBLISH))
    }

    private fun replaceFragment(fragment: Fragment) {
        print(4)
        val fragmentManager= activity?.supportFragmentManager
        print(4)
        val transaction = fragmentManager?.beginTransaction()
        print(5)
        if (transaction != null) {
            print(8)
            transaction.replace(R.id.orderdetail,fragment)
            transaction.commit()
            print(7)
        }
        print(6)
    }
}

