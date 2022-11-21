package com.example.myapplication.ui.activity.Dj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_jy.*

class JyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jy)

        supportActionBar?.hide()
        l.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        l.setOnClickListener { finish() }
    }
}