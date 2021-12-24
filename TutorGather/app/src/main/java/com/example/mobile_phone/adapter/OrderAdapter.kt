package com.example.mobile_phone.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mobile_phone.R
import com.example.mobile_phone.bean.Order

class OrderAdapter(context:Context, private val resourceId:Int, data:List<Order>) : ArrayAdapter<Order>(context, resourceId, data) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view:View
        if(convertView == null)
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        else
            view = convertView

        val subjectName: TextView = view.findViewById(R.id.textViewSubject)
        val userImage: ImageView = view.findViewById(R.id.imageViewUser)
        val abstract: TextView = view.findViewById(R.id.textViewAbstract)
        val grade: TextView = view.findViewById(R.id.textViewGrade)
        // 获取当前order的数据 并放置order信息到item中
        val order = getItem(position)
        if(order != null){
            subjectName.text = order.subject
            abstract.text = order._abstract
            grade.text = order.grade
            userImage.setImageResource(order.imageResourceId)
        }
        return view;
    }
}