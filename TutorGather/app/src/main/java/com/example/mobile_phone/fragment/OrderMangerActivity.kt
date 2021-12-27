package com.example.mobile_phone.fragment

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobile_phone.R

class OrderMangerActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sentButton:Button = this.findViewById(R.id.Sentbutton)
        val acceptButton:Button = this.findViewById(R.id.Acceptbutton)
        val completedButton :Button = this.findViewById(R.id.Completedbutton)
        sentButton.setOnClickListener{
            replaceFragment(OrderDisplayFragment(1))
        }
        acceptButton.setOnClickListener{
            replaceFragment(OrderDisplayFragment(1))
        }
        completedButton.setOnClickListener{
            replaceFragment(OrderDisplayFragment(1))
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val transaction =fragmentManager.beginTransaction()
        transaction.replace(R.id.orderdetail,fragment)
        transaction.commit()
    }
}