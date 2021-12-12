package com.example.mobile_phone

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class StorageSetting(private val context: Context, name: String, version: Int) : SQLiteOpenHelper(context, name, null, version){
    private val createTableSQl = "create table  journal (" +
            "id integer primary key autoincrement," +
            "author text," +
            "title text," +
            "content text)"

    // 表格只会创建
    override fun onCreate(db: SQLiteDatabase?) {
        if(db == null) {
            Toast.makeText(context, "db is null", Toast.LENGTH_SHORT).show()
            return;
        }
        db.execSQL(createTableSQl)
        Toast.makeText(context, "db Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        return
    }



}