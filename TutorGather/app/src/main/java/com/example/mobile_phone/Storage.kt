package com.example.mobile_phone

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf

import java.util.*


object Storage {
    private val tableName = "journal"

    // 单身汉模式
    private val storage: Storage? = null

    private lateinit var db: SQLiteDatabase

    fun init(_db: SQLiteDatabase) {
        this.db = _db
    }

    fun putJournal(journal: Journal) {
        val sql:String
        // 需要先检查journal是否为空
        if(journal.id == 0)
            sql = "INSERT INTO $tableName (author, title, content) VALUES ('${journal.author}','${journal.title}', '${journal.content}')"
        else
            sql = "UPDATE $tableName SET title = '${journal.title}', content = '${journal.content}' WHERE id = ${journal.id}"

        db.execSQL(sql)
    }

    @SuppressLint("Range")
    fun getAllJournal(): Vector<Journal> {
        val outputs = Vector<Journal>()
        val cursor = db.query(tableName, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val author = cursor.getString(cursor.getColumnIndex("author"))
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val content = cursor.getString(cursor.getColumnIndex("content"))
            outputs.add(Journal(id, author, title, content))
        }
        cursor.close()
        return outputs
    }

    @SuppressLint("Range")
    fun getJournalById(id: Int): Journal {
        // Filter results WHERE "title" = 'My Title'
        val selection = "id = ?"
        val selectionArgs = arrayOf("$id")
        val cursor = db.query(tableName, null, selection, selectionArgs, null, null, null, null)
        if (cursor.moveToNext()) {
            return Journal(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("author")),
                cursor.getString(cursor.getColumnIndex("title")),
                cursor.getString(cursor.getColumnIndex("content"))
            )
        }
        else {
            return Journal(0, "", "标题", "内容")
        }
    }

    fun deleteJournal(id: Int) {
        val sql = "DELETE FROM $tableName WHERE id = $id"
        db.execSQL(sql)
    }


}