package com.example.myapplication.ui.activity.Dj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_g_o_v.*
import kotlinx.android.synthetic.main.activity_zhihui_content.*

class ZhihuiContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zhihui_content)
        ii.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        ii.setOnClickListener { finish() }
        supportActionBar?.hide()
    }
}