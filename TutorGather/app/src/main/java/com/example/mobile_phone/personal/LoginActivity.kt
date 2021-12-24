package com.example.mobile_phone.personal

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mobile_phone.MainActivity
import com.example.mobile_phone.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btn_res : Button = findViewById(R.id.button_register)

        btn_res.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        val btn_login : Button = findViewById(R.id.button_login)
        btn_login.setOnClickListener {
            var count_text : TextView = findViewById(R.id.count_try)
            var counter : Int = count_text.text.toString().toInt()

            val prefs_users = getSharedPreferences("allusers", Context.MODE_PRIVATE)
            val input_username : EditText = findViewById(R.id.edit_username)
            val input_password : EditText = findViewById(R.id.edit_password)
            val real_password = prefs_users.getString(input_username.text.toString(),"")

            val err_user : String = getString(R.string.error_blank_username)
            val err_pw : String = getString(R.string.error_blank_password)
            val err_both : String = getString(R.string.error_blank_both)
            val err_both_2 : String = getString(R.string.error_blank_both_2)

            when{
                input_username.text.toString().isEmpty()&&input_password.text.toString().isEmpty()
                -> {
                    Toast.makeText(this, err_both, Toast.LENGTH_SHORT).show()
                    Toast.makeText(this,err_both_2, Toast.LENGTH_SHORT).show()
                    counter -= 1
                    count_text.text = counter.toString()
                }
                input_username.text.toString().isEmpty()
                -> {
                    Toast.makeText(this, err_user, Toast.LENGTH_SHORT).show()
                    counter -= 1
                    count_text.text = counter.toString()
                }
                input_password.text.toString().isEmpty()
                -> {
                    Toast.makeText(this, err_pw, Toast.LENGTH_SHORT).show()
                    counter -= 1
                    count_text.text = counter.toString()
                }
                else -> {
                    if (input_password.text.toString()!=real_password){ // 判断密码是否正确
                        val err_info_1 :String = getString(R.string.error_incorrect_1)
                        val err_info_2 :String = getString(R.string.error_incorrect_2)
                        Toast.makeText(this,err_info_1, Toast.LENGTH_SHORT).show()
                        Toast.makeText(this,err_info_2, Toast.LENGTH_SHORT).show()
                        counter -= 1
                        count_text.text = counter.toString()
                    }
                    else{ // 成功
                        val intent = Intent(this, MainActivity::class.java)
                        counter = 3
                        count_text.text = counter.toString()
                        startActivity(intent)
                    }
                }
            }

            if (counter == 0){ // 最后再检查次数
                val alert : AlertDialog.Builder = AlertDialog.Builder(this)

                alert.setMessage(getString(R.string.alert_info))
                alert.setPositiveButton(getString(R.string.alert_ok)){
                        _: DialogInterface, _: Int -> finish()
                }
                val alertDialog = alert.create()
                alertDialog.setTitle(getString(R.string.alert_title))
                alertDialog.show()
            }

        }
    }

}