package com.example.mobile_phone

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_phone.webData.UserWebData
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.concurrent.thread

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val btn_res: Button = findViewById(R.id.btn_register)

        btn_res.setOnClickListener {
            val editor = getSharedPreferences("allusers", Context.MODE_PRIVATE).edit();
            val user_name: EditText = findViewById(R.id.edit_new_user)
            val userPhone: EditText = findViewById(R.id.edit_phone_number)
            val password: EditText = findViewById(R.id.edit_password)
            val conf_password: EditText = findViewById(R.id.edit_conf_password)

            val err_user: String = getString(R.string.error_blank_username)
            val err_pw: String = getString(R.string.error_blank_password)
            val err_conf: String = getString(R.string.error_blank_conf_password)

            when {
                user_name.text.toString().isEmpty() -> {
                    Toast.makeText(this, err_user, Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                password.text.toString().isEmpty() -> {
                    Toast.makeText(this, err_pw, Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                conf_password.text.toString().isEmpty() -> {
                    Toast.makeText(this, err_conf, Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            if (password.text.toString() != conf_password.text.toString()) {
                val err_password: String = getString(R.string.error_different_password)
                Toast.makeText(this, err_password, Toast.LENGTH_LONG).show()
            }
            else {
                editor.putString(user_name.text.toString(), password.text.toString())
                editor.apply()
                thread {
                    if (UserWebData.register(
                            userPhone.text.toString(),
                            password.text.toString(),
                            user_name.text.toString()
                        )
                    ) {
                        val suc_register: String = getString(R.string.succ_register)
                        runOnUiThread {
                            Toast.makeText(this, suc_register, Toast.LENGTH_LONG).show()
                            finish()
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this, "手机号已经被注册", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        }
    }
}