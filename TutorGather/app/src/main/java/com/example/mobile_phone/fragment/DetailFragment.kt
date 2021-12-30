package com.example.mobile_phone.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_phone.adapter.DetailAdapter
import com.example.mobile_phone.bean.TitleAndContent
import com.example.mobile_phone.bean.User
import com.example.mobile_phone.databinding.FragmentOrderdetailBinding
import com.example.mobile_phone.enum.OrderStatus
import com.example.mobile_phone.enum.UserStatus
import com.example.mobile_phone.webData.OrderWebData
import kotlin.concurrent.thread

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {
    private var orderId:Int = 9
    private var detailList:ArrayList<TitleAndContent> = ArrayList()
    private lateinit var detailAdapter: DetailAdapter
    private var _binding: FragmentOrderdetailBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use the Kotlin extension in the fragment-ktx artifact
        println("requestKey")
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            orderId  = bundle.getInt("orderId")
            // 加载订单详情
            thread {
                try {
                    val order = OrderWebData.getOrderByOrderId(orderId)
                    detailList.add(TitleAndContent("所在年级", order.grade))
                    detailList.add(TitleAndContent("科目", order.subject))
                    detailList.add(TitleAndContent("开始时间", order.startTime))
                    detailList.add(TitleAndContent("结束时间", order.endTime))
                    detailList.add(TitleAndContent("地址", order.address))
                    detailList.add(TitleAndContent("详情", order.detail))
                    detailList.add(TitleAndContent("费用", order.expense))
                    if (this.activity == null)
                        println("this activity is null")
                    else {
                        this.activity?.runOnUiThread {
                            detailAdapter.notifyDataSetChanged()
                            println(order)
                            // 是自己的订单时, 则只能撤销,  或者该当前身份是老师且teacherId为UserId, 也能撤销
                            if(OrderStatus.fromInt(order.status) == OrderStatus.ACCEPT && (order.belongID == User.id || (User.id == order.teacherID && User.status == UserStatus.TEACHER))) {
                                binding.buttonDetail.text = "撤销订单"
                                binding.buttonDetail.setOnClickListener {
                                    thread {
                                        OrderWebData.revokeOrder(order.id)
                                    }
                                    Toast.makeText(this.requireContext(), "撤销成功", Toast.LENGTH_SHORT).show()
                                }
                            }
                            // 身份为老师
                            else if(OrderStatus.fromInt(order.status) == OrderStatus.PUBLISH && User.status == UserStatus.TEACHER) {
                                binding.buttonDetail.text = "接受订单"
                                binding.buttonDetail.setOnClickListener {
                                    thread {
                                        OrderWebData.acceptOrder(order.id, User.id)
                                    }
                                    Toast.makeText(this.requireContext(), "接受成功", Toast.LENGTH_SHORT).show()
                                }
                            }
                            // 其余情况关闭按钮
                            else {
                                binding.buttonDetail.visibility = View.GONE
                            }
                        }
                    }
                }
                catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderdetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 绑定recycleOrderDetail
        detailAdapter = DetailAdapter(detailList)
        binding.recyclerViewOrderDetail.adapter = detailAdapter
        binding.recyclerViewOrderDetail.layoutManager = LinearLayoutManager(this.requireContext())

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}