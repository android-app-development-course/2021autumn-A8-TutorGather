package com.example.mobile_phone.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_phone.R
import com.example.mobile_phone.SQLite.DatabaseHelper
import com.example.mobile_phone.adapter.ChattingAdapter
import com.example.mobile_phone.bean.SingleMessage
import com.example.mobile_phone.bean.User
import kotlinx.android.synthetic.main.fragment_chat.*


class ChatFragment : Fragment() {
    private val msgList = ArrayList<SingleMessage>()
    private var adapter : ChattingAdapter? = null

    private fun initMsg(){
        val msg1 = SingleMessage("龚爸爸，孩子上周作业表现不错，有很明显的进步！",SingleMessage.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = SingleMessage("谢谢老师的辅导。",SingleMessage.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = SingleMessage("咱们这周六您看可以继续上课吗？",SingleMessage.TYPE_RECEIVED)
        msgList.add(msg3)
        val msg4 = SingleMessage("孩子这周末有点事",SingleMessage.TYPE_SENT)
        msgList.add(msg4)
        val msg5 = SingleMessage("您什么时候有时间呢？",SingleMessage.TYPE_RECEIVED)
        msgList.add(msg5)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val dbHelper = DatabaseHelper(this.requireActivity(),"localChats.db",5)
//        val db = dbHelper.writableDatabase
//        val cursor = db.query(
//            "ChatHistory",
//            null,
//            "sender_id=? and receiver_id=?",
//            arrayOf(User.id.toString(),),
//            null,
//            null,
//            null
//        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMsg()
        val layoutManger = LinearLayoutManager(this.requireActivity())
        recycle_chatting.layoutManager = layoutManger
        adapter = ChattingAdapter(msgList)
        recycle_chatting.adapter = adapter
        btn_send.setOnClickListener {
            val content = text_toBeSent.text.toString()
            if(content.isNotEmpty()){
                val msg = SingleMessage(content,SingleMessage.TYPE_SENT)
                msgList.add(msg)
                adapter?.notifyItemInserted(msgList.size-1)
                recycle_chatting.scrollToPosition(msgList.size-1)
                text_toBeSent.setText("")
            }
        }
    }
}