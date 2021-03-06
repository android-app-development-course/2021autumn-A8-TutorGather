package com.example.mobile_phone

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.mobile_phone.SQLite.DatabaseHelper
import com.example.mobile_phone.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // 用于底部按钮栏触发事件
        val bottomNavigation:BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener{
            menuitem ->
            when(menuitem.itemId) {
                R.id.page_header -> {
                    navController.navigate(R.id.action_global_to_orderHeader)
                }
                R.id.page_manger -> {
                    navController.navigate(R.id.action_global_to_orderFragment)
                }
                R.id.page_message -> {
                    navController.navigate(R.id.action_global_messagesFragment)
                }
                R.id.page_user -> {
                    navController.navigate(R.id.action_global_personalFragment)
                }
            }
            true
        }
        // 允许在主线程进行网络请求
        val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val dbHelper = DatabaseHelper(this,"localChats.db",5)
        val db = dbHelper.writableDatabase
        val value1 = ContentValues().apply {
            put("from_id","2")
            put("from_name","周老师")
            put("messages","您什么时候有时间呢？")
        }
        db.insert("LatestChats",null,value1)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
    private fun changeCurrentFragment(targetFragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
////        transaction.replace(, targetFragment,null)
//        transaction.commit()

    }
}