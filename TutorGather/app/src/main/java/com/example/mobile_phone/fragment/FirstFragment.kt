package com.example.mobile_phone.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.mobile_phone.R
import com.example.mobile_phone.adapter.OrderAdapter
import com.example.mobile_phone.bean.Order
import com.example.mobile_phone.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderList = listOf(Order("小明", "编程老师速来", "一年级", R.mipmap.ic_launcher))

        val adapter = OrderAdapter(this.requireContext(), R.layout.order_item, orderList)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener {parent, _view, position, id ->
//            setFragmentResult("requestKey", bundleOf("journalId" to journalVec[position].id))
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}