package com.example.mobile_phone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_phone.R
import com.example.mobile_phone.bean.TitleAndContent

class DetailAdapter(private val resourceId: List<TitleAndContent>) :
    RecyclerView.Adapter<DetailAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val detailTitle: TextView
        val detailContent: TextView
        init {
            // Define click listener for the ViewHolder's View.
            detailTitle = view.findViewById(R.id.detailTitle)
            detailContent = view.findViewById(R.id.detailContent)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, 绑定xml文件
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_detail, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.detailTitle.text = resourceId[position].title
        holder.detailContent.text = resourceId[position].content
    }

    override fun getItemCount() = resourceId.size



}