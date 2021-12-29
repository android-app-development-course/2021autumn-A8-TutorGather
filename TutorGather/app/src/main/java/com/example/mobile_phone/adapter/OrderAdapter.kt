package com.example.mobile_phone.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_phone.R
import com.example.mobile_phone.bean.Order

class OrderAdapter(private val data:List<Order>, private val fragment:Fragment) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subjectName: TextView
        val userImage: ImageView
        val abstract: TextView
        val grade: TextView
        init {
            // Define click listener for the ViewHolder's View.
            subjectName = view.findViewById(R.id.textViewSubject)
            userImage = view.findViewById(R.id.imageViewUser)
            abstract =  view.findViewById(R.id.textViewAbstract)
            grade = view.findViewById(R.id.textViewGrade)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, 绑定xml文件
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_order, viewGroup, false)
        val viewHolder = ViewHolder(view)

        viewHolder.itemView.setOnClickListener{
            val position = viewHolder.adapterPosition
            fragment.setFragmentResult("requestKey", bundleOf("orderId" to data[position].id))
            findNavController(view).navigate(R.id.action_fragment_header_to_fragment_detail)
        }

        return viewHolder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 获取当前order的数据 并放置order信息到item中
        val order = data[position]
        holder.subjectName.text = order.subject
        holder.abstract.text = order._abstract
        holder.grade.text = order.grade
        holder.userImage.setImageResource(order.imageResourceId)
    }

    override fun getItemCount(): Int = data.size
}