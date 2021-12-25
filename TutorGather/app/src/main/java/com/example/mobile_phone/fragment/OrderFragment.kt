package com.example.mobile_phone.fragment

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobile_phone.R
<<<<<<< HEAD

=======
import com.example.mobile_phone.bean.User
import kotlinx.android.synthetic.main.order_select_fragment.*
>>>>>>> c03b6983e250f4bcc862cf64babc03330151d19a

class OrderFragment:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val Sentbutton:Button = this.findViewById(R.id.Sentbutton)
        val Acceptbutton:Button = this.findViewById(R.id.Acceptbutton)
        val Completedbutton :Button = this.findViewById(R.id.Completedbutton)
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

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val transaction =fragmentManager.beginTransaction()
        transaction.replace(R.id.orderdetail,fragment)
        transaction.commit()
    }
}