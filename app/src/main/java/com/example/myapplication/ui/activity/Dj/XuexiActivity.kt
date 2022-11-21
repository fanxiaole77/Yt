package com.example.myapplication.ui.activity.Dj

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_xuexi.*

class XuexiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xuexi)


        supportActionBar?.hide()
        pp.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        pp.setOnClickListener { finish() }
        val uri  = Uri.parse("android.resource://$packageName/${R.raw.video01}")
        V.setVideoURI(uri)
        V.start()

    }
}