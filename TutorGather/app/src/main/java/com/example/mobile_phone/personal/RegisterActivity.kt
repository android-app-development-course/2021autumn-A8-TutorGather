package com.example.mobile_phone.personal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mobile_phone.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val btn_res : Button = findViewById(R.id.btn_register)
        btn_res.setOnClickListener{
            val editor = getSharedPreferences("allusers", Context.MODE_PRIVATE).edit();
            val user_name : EditText =findViewById(R.id.edit_new_user)
            val password : EditText =findViewById(R.id.edit_password)
            val conf_password : EditText = findViewById(R.id.edit_conf_password)

            val err_user : String = getString(R.string.error_blank_username)
            val err_pw : String = getString(R.string.error_blank_password)
            val err_conf : String = getString(R.string.error_blank_conf_password)

            when{
                user_name.text.toString().isEmpty() ->{
                    Toast.makeText(this,err_user, Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                password.text.toString().isEmpty() ->{
                    Toast.makeText(this,err_pw, Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                conf_password.text.toString().isEmpty() ->{
                    Toast.makeText(this,err_conf, Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val prefs_users = getSharedPreferences("allusers", Context.MODE_PRIVATE)
            val real_password = prefs_users.getString(user_name.text.toString(),"")
            if (!real_password.isNullOrEmpty()){
                val err_exist : String = getString(R.string.error_user_exist)
                Toast.makeText(this,err_exist, Toast.LENGTH_SHORT).show()
            }

            else if(password.text.toString() != conf_password.text.toString()){
                val err_password : String = getString(R.string.error_different_password)
                Toast.makeText(this,err_password, Toast.LENGTH_LONG).show()
            }
            else{
                editor.putString(user_name.text.toString(),password.text.toString())
                editor.apply()

                val suc_register :String = getString(R.string.succ_register)
                Toast.makeText(this,suc_register, Toast.LENGTH_LONG).show()
                finish()
            }

        }
    }
}