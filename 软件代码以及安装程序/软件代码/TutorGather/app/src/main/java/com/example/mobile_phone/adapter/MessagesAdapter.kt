package com.example.mobile_phone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_phone.R
import com.example.mobile_phone.bean.MessagePreview

class MessagesAdapter(private val data:List<MessagePreview>, private val fragment: Fragment) : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView
        val content: TextView
        val img: ImageView
        init {
            // Define click listener for the ViewHolder's View.
            title = view.findViewById(R.id.title_msg_pre)
            content = view.findViewById(R.id.content_msg_pre)
            img =  view.findViewById(R.id.preview_image_profile)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, 绑定xml文件
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_message_preview, viewGroup, false)
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener{
            val position = viewHolder.adapterPosition
//            fragment.setFragmentResult("requestKey", bundleOf("orderId" to data[position].contacts))
            Navigation.findNavController(view).navigate(R.id.action_messagesFragment_to_chatFragment)
        }

        return viewHolder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 获取当前order的数据 并放置order信息到item中
        val preMsg = data[position]
        holder.title.text = preMsg.title
        holder.content.text = preMsg.latest_text
        holder.img.setImageResource(preMsg.icon)
    }

    override fun getItemCount(): Int = data.size
}