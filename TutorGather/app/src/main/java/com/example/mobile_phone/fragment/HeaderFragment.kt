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
import com.to.aboomy.pager2banner.IndicatorView


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HeaderFragment : Fragment() {
    private var _binding: FragmentHeaderBinding? = null

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

        val orderList = listOf(
            Order("Root", "编程老师速来", "一年级", R.mipmap.ic_launcher),
            Order("小明", "编程老师速来", "一年级", R.mipmap.ic_launcher)
        )

        val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener { parent, _view, position, id ->
//            setFragmentResult("requestKey", bundleOf("journalId" to journalVec[position].id))
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}