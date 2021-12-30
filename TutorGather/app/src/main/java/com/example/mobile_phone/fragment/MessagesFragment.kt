package com.example.mobile_phone.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_phone.R
import com.example.mobile_phone.SQLite.DatabaseHelper
import com.example.mobile_phone.adapter.MessagesAdapter
import com.example.mobile_phone.bean.MessagePreview
import kotlinx.android.synthetic.main.fragment_messages.*
import kotlinx.android.synthetic.main.fragment_orderdetail.*

class MessagesFragment : Fragment() {
    private val latestMsgList = ArrayList<MessagePreview>()

    @SuppressLint("Range")
    private fun initPreMsg(){
        val dbHelper = DatabaseHelper(this.requireActivity(),"localChats.db",5)
        val db = dbHelper.writableDatabase
        val cursor = db.query(
            "LatestChats",
            null,
            null,
            null,
            null,
            null,
            null
        )
        if (latestMsgList.isEmpty() && cursor.moveToFirst()){
            do {
                val msgFrom = cursor.getString(cursor.getColumnIndex("from_name"))
                val msg = cursor.getString(cursor.getColumnIndex("messages"))
                latestMsgList.add(MessagePreview(msgFrom,msg))
            }while (cursor.moveToNext())
        }
        cursor.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPreMsg()
        val layoutManager = LinearLayoutManager(this.requireActivity())
        recycle_preview_message.layoutManager = layoutManager
        val adapter = MessagesAdapter(latestMsgList,this)
        recycle_preview_message.adapter = adapter
    }
}