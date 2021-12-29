package com.example.mobile_phone.fragment

import android.content.ContentValues.TAG
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.mobile_phone.R
import com.example.mobile_phone.bean.User
import com.example.mobile_phone.enum.UserStatus
import kotlinx.android.synthetic.main.fragment_personal.*

class PersonalFragment : Fragment() {
    private lateinit var profile_img: ImageButton

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

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profile_img = profile_image
        profile_img.setImageResource(R.drawable.default_profile_image_128)

        val user_name: TextView = name_content
        user_name.text = User.name

        val identification: TextView = id_content
        if (User.status == UserStatus.TEACHER) {
            identification.text = "老师"
        } else {
            identification.text = "家长"
        }
        val address: TextView = address_content
        address.text = User.address
        // 点击头像更换图片
        profile_img.setOnClickListener {
            getContent.launch("image/*")
        }
        // 点击按钮切换身份
        button_change_identity.setOnClickListener {
            if(User.status == UserStatus.TEACHER) {
                User.status = UserStatus.PARENT
                identification.text = "家长"
            }
            else {
                User.status = UserStatus.TEACHER
                identification.text = "老师"
            }
        }

    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned Uri
        if (uri != null) {
            profile_img.setImageBitmap(getBitmapFromUri(uri))
        } else {
            Log.e(TAG, "getContent: url为空")
        }
    }

    private fun getBitmapFromUri(uri: Uri) = this.activity!!.contentResolver.openFileDescriptor(uri, "r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }

}