package com.example.mobile_phone.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobile_phone.R
import com.example.mobile_phone.adapter.BannerAdapter
import com.example.mobile_phone.adapter.OrderAdapter
import com.example.mobile_phone.bean.Order
import com.example.mobile_phone.databinding.FragmentHeaderBinding
import com.example.mobile_phone.webData.OrderWebData
import com.to.aboomy.pager2banner.IndicatorView
import kotlin.concurrent.thread


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HeaderFragment : Fragment() {
    private var _binding: FragmentHeaderBinding? = null
    private val ordersList:ArrayList<Order> = ArrayList<Order>()
    private lateinit var orderAdapter:OrderAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderAdapter = OrderAdapter(this.requireContext(), R.layout.order_item, ordersList)
        binding.listView.adapter = orderAdapter
        binding.listView.setOnItemClickListener { parent, _view, position, id ->
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        //使用内置Indicator
        val indicator = IndicatorView(this.requireActivity())
            .setIndicatorColor(Color.DKGRAY)
            .setIndicatorSelectorColor(Color.WHITE)

        val imageId = listOf(R.drawable.banner_image_1, R.drawable.banner_image_2)
        //创建adapter
        val bannerAdapter = BannerAdapter(imageId)
        //传入RecyclerView.Adapter 即可实现无限轮播
        binding.banner.setIndicator(indicator).adapter = bannerAdapter
        // 使用orderWebdata
        thread{
            try {
                val orderWebData = OrderWebData()
                // 不能修改ordersList的指向
                for (order in orderWebData.getRandomOrders(5)){
                    ordersList.add(order)
                }
                if(this.activity == null)
                    println("this activity is null")
                else {
                    println("ordersList is change")
                    this.activity?.runOnUiThread {
                        orderAdapter.notifyDataSetChanged()

                    }
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}