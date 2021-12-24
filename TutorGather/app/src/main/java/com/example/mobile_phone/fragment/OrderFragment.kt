package com.example.mobile_phone.fragment

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobile_phone.R


class OrderFragment:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val Sentbutton:Button = this.findViewById(R.id.Sentbutton)
        val Acceptbutton:Button = this.findViewById(R.id.Acceptbutton)
        val Completedbutton :Button = this.findViewById(R.id.Completedbutton)
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