package com.example.mobile_phone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobile_phone.R
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
            replaceFragment(OrderDisplayFragment(1))
        }
        Acceptbutton.setOnClickListener{
            replaceFragment(OrderDisplayFragment(1))
        }
        Completedbutton.setOnClickListener{
            replaceFragment(OrderDisplayFragment(1))
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager= activity?.supportFragmentManager
        val transaction = fragmentManager?.beginTransaction()
        if (transaction != null) {
            transaction.replace(R.id.orderdetail,fragment)
            transaction.commit()
        }
    }
}

