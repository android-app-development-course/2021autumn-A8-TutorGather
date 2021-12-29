package com.example.mobile_phone.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mobile_phone.R
import com.example.mobile_phone.bean.User
import kotlinx.android.synthetic.main.fragment_personal.*

class PersonalFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profile_img :ImageView = profile_image
        profile_img.setImageResource(R.drawable.default_profile_image_128)

//        val options: BitmapFactory.Options = BitmapFactory.Options()
//        options.inDither = false
//        options.inPurgeable = true
//        options.inInputShareable = true
//        options.inTempStorage = ByteArray(1024 * 32)
//
//        val bm: Bitmap =
//            BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length, options)
//        alert_photo.setImageBitmap(bm)
        val user_name : TextView = name_content
        user_name.text = User.name

        val identification : TextView = id_content
        if (User.canBeTeacher){
            identification.text = "老师"
        }
        else{
            identification.text = "家长"
        }
        val address :TextView = address_content
        address.text = User.address

//        user_name.setOnClickListener {
//            user_name.text = User.id.toString()
//        }
    }

}