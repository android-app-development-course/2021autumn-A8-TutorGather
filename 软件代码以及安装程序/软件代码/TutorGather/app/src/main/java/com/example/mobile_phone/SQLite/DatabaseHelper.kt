package com.example.mobile_phone.SQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelper (val context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {
    private val createReceivedChats = "create table ReceivedChats (" +
            "id integer primary key autoincrement," +
            "from_id int," +
            "messages text,"+
            "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")"
    private val createSentChats = "create table SentChats (" +
            "id integer primary key autoincrement," +
            "to_id int," +
            "messages text,"+
            "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")"
    private val createLatestChats = "create table LatestChats (" +
            "id integer primary key autoincrement," +
            "from_id int," +
            "from_name text," +
            "messages text,"+
            "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createReceivedChats)
        db.execSQL(createSentChats)
        db.execSQL(createLatestChats)
//        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {   }
}