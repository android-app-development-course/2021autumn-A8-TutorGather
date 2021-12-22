package com.example.mobile_phone.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobile_phone.R
import kotlinx.android.synthetic.main.order_select_fragment.*

class OrderFragment:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Sentbutton.setOnClickListener{
            replaceFragment(OrderDisplayFragment())
        }
        Acceptbutton.setOnClickListener{
            replaceFragment(OrderDisplayFragment())
        }
        Completedbutton.setOnClickListener{
            replaceFragment(OrderDisplayFragment())
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val transaction =fragmentManager.beginTransaction()
        transaction.replace(R.id.orderdetail,fragment)
        transaction.commit()
    }
}