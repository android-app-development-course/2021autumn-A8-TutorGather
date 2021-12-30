package com.example.mobile_phone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_phone.R
import com.example.mobile_phone.bean.SingleMessage
import java.lang.IllegalArgumentException

class ChattingAdapter(val msgList: List<SingleMessage>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class LeftViewHolder (view: View):RecyclerView.ViewHolder(view){
        val leftMsg :TextView = view.findViewById(R.id.msg_left)
    }
    inner class RightViewHolder (view: View):RecyclerView.ViewHolder(view){
        val RightMsg :TextView = view.findViewById(R.id.msg_right)
    }

    override fun getItemViewType(position: Int): Int {
        val msg = msgList[position]
        return msg.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= if (viewType==SingleMessage.TYPE_RECEIVED){
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chatting_left,parent,false)
        LeftViewHolder(view)
    }else{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chatting_right,parent,false)
        RightViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = msgList[position]
        when(holder){
            is LeftViewHolder -> holder.leftMsg.text = msg.content
            is RightViewHolder -> holder.RightMsg.text = msg.content
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount()=msgList.size
}