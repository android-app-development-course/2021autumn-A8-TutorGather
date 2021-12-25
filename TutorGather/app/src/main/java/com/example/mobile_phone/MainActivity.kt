package com.example.mobile_phone

<<<<<<< HEAD
import android.annotation.SuppressLint
=======
import android.os.Build
>>>>>>> c03b6983e250f4bcc862cf64babc03330151d19a
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
import com.example.mobile_phone.bean.User
import com.example.mobile_phone.databinding.ActivityMainBinding
import com.example.mobile_phone.fragment.OrderDisplayFragment;
import kotlinx.android.synthetic.main.order_select_fragment.*

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
<<<<<<< HEAD
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.item1 -> {
                    true
                }
                R.id.item2 -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
//        //以下为订单管理页面的测试
//        setContentView(R.layout.order_fragment)
//        Sentbutton.setOnClickListener{
//            replaceFragment(OrderDisplayFragment())
//        }
//        Acceptbutton.setOnClickListener{
//            replaceFragment(OrderDisplayFragment())
//        }
//        Completedbutton.setOnClickListener{
//            replaceFragment(OrderDisplayFragment())
//        }
//        replaceFragment(OrderDisplayFragment())
=======
        if (Build.VERSION.SDK_INT > 9) {
            val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        //以下为订单管理页面的测试
        setContentView(R.layout.order_fragment)
        Sentbutton.setOnClickListener{
            replaceFragment(OrderDisplayFragment(0))
        }
        Acceptbutton.setOnClickListener{
            replaceFragment(OrderDisplayFragment(1))
        }
        Completedbutton.setOnClickListener{
            replaceFragment(OrderDisplayFragment(2))
        }
        replaceFragment(OrderDisplayFragment(0))
>>>>>>> c03b6983e250f4bcc862cf64babc03330151d19a
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager=supportFragmentManager
        val transaction =fragmentManager.beginTransaction()
        transaction.replace(R.id.orderdetail,fragment)
        transaction.commit()
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
}