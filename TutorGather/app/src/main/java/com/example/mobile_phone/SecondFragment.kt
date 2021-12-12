package com.example.mobile_phone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.mobile_phone.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private var journal = Journal()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use the Kotlin extension in the fragment-ktx artifact
        // 设置结果监听器
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            journal = Storage.getJournalById(bundle.getInt("journalId"))
            println(" result id : ${journal.id}")
            view!!.findViewById<EditText>(R.id.editTextTextTitle).setText(journal.title)
            view!!.findViewById<EditText>(R.id.editTextCotent).setText(journal.content)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 保存
        binding.buttonSave.setOnClickListener {
            // 放置日志
            Storage.putJournal(Journal(journal.id, "", _binding!!.editTextTextTitle.text.toString(), _binding!!.editTextCotent.text.toString()))
            // 跳转回去
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}