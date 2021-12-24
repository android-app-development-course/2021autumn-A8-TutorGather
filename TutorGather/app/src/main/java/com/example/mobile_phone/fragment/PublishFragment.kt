package com.example.mobile_phone.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobile_phone.R
import com.example.mobile_phone.bean.Order
import com.example.mobile_phone.databinding.FragmentPublishBinding
import com.example.mobile_phone.webData.OrderWebData
import kotlin.concurrent.thread


class PublishFragment : Fragment(R.layout.fragment_publish)  {
    private var _binding: FragmentPublishBinding? = null
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPublishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 年龄adapter
        val gradeAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_dropdown_item_1line, GRADE
        )
        val textViewGrade: MultiAutoCompleteTextView = binding.multiAutoCompleteTextViewGrade
        textViewGrade.setAdapter(gradeAdapter)
        // 需要提供一个Tokenizer 辨别多种子串
        textViewGrade.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        // 科目adapter
        val subjectAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_dropdown_item_1line, GRADE
        )
        binding.buttonPublishConfirm.setOnClickListener {
            val gradeText = binding.multiAutoCompleteTextViewGrade.text.toString().trim()
            val subjectText = binding.multiAutoCompleteTextViewGrade.text.toString().trim()
            val abstractText = binding.editTextTextMultiLineAbstract.text.toString().trim()
            val location = binding.editTextTextLocation.text.toString().trim()
            // 开始时间 : 结束时间  格式要求 YYYY-MM-DD
            val timeTextList = binding.editTextTime.text.toString().split(":")
            if(timeTextList.size < 2){
                Toast.makeText(this.context, "日期格式不对,应为 YYYY-MM-DD : YYYY-MM-DD", Toast.LENGTH_SHORT).show()
            }
            else {
                val detail = binding.editTextTextMultiLineDetail.text.toString().trim()
                val expense = binding.editTextExpense.text.toString().trim()
                // 默认用户id为2, 手机号码为 1234567
                // 五年级
                val order = Order(
                    0,
                    gradeText,
                    subjectText,
                    abstractText,
                    timeTextList[0].trim(),
                    timeTextList[1].trim(),
                    location,
                    detail,
                    expense,
                    0,
                    "1234567",
                    2
                )
                thread {
                    val orderWebData: OrderWebData = OrderWebData()
                    if(orderWebData.publishOrder(order)) {
                        this.activity?.runOnUiThread {
                            Toast.makeText(this.context, "发布成功", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        this.activity?.runOnUiThread {
                            Toast.makeText(this.context, "发布失败, 请联系开发者", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // 年龄补全
    private val GRADE = arrayOf(
        "高一", "高二", "高三", "初一", "初二", "test"
    )

    // 科目补全
    private val SUBJECT = arrayOf(
        "语文", "数学", "英语", "物理", "C++", "Python"
    )
}