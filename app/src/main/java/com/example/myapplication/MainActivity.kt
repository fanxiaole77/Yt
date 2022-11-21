package com.example.myapplication

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.ui.`fun`.FunFragment
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.me.MeFragment
import com.example.myapplication.ui.news.NewsFragment
import com.example.myapplication.ui.sj.SjFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var bottom11:BottomNavigationView
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        window.statusBarColor = Color.TRANSPARENT
        SmartCiryApplication.TOKEN = sharedPreferences.getString("token","").toString()
        loadFragment(HomeFragment())
        bottom11 = findViewById(R.id.bottom)

        bottom.setOnNavigationItemSelectedListener {
            when(it?.itemId){
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                }
                R.id.nav_dun -> {
                    loadFragment(FunFragment())
                }
                R.id.nav_fx -> {
                    loadFragment(SjFragment())
                }
                R.id.nav_news -> {
                    loadFragment(NewsFragment())
                }
                R.id.nav_me -> {
                    loadFragment(MeFragment())
                }
            }
            true
        }
    }

    private fun loadFragment(fm:Fragment){
        val aa = supportFragmentManager.beginTransaction()
        aa.replace(R.id.fragment,fm)
        aa.commit()

    }

}