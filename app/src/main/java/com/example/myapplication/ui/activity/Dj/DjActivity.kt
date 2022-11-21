package com.example.myapplication.ui.activity.Dj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.activity_dj.*

class DjActivity : AppCompatActivity() {
    private val array = arrayListOf(
        R.drawable.banner1,
        R.drawable.banner2,
        R.drawable.banner3
    )
    private var Dj_Banner: Banner<Int, BannerImageAdapter<Int>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dj)

        supportActionBar?.hide()
        tt.setNavigationIcon(R.drawable.danghui)

        Dj_Banner = findViewById(R.id.dj_Banner)

        Dj_Banner?.apply {
            setAdapter(object :BannerImageAdapter<Int>(array){
                override fun onBindView(p0: BannerImageHolder?, p1: Int?, p2: Int, p3: Int) {
                    Glide.with(p0!!.imageView).load(array[p2]).into(p0.imageView)
                }
            })
        }

        bt_1.setOnClickListener {
            startActivity(Intent(this,ZhihuiContentActivity::class.java))
        }

        bt_2.setOnClickListener {
            startActivity(Intent(this,ZhihuiContentActivity::class.java))
        }

        bt_3.setOnClickListener {
            startActivity(Intent(this,ZhihuiContentActivity::class.java))
        }

        bt_4.setOnClickListener {
            startActivity(Intent(this,ZhihuiContentActivity::class.java))
        }

        dongtai.setOnClickListener {
            startActivity(Intent(this,DtActivity::class.java))
        }
        xuexi.setOnClickListener {
            startActivity(Intent(this,XuexiActivity::class.java))
        }
        jinayan.setOnClickListener {
            startActivity(Intent(this,JyActivity::class.java))
        }
        paizhao.setOnClickListener {
            startActivity(Intent(this,PzActivity::class.java))
        }

    }

}