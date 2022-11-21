package com.example.myapplication.ui.activity.Dj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.activity_dt.*

class DtActivity : AppCompatActivity() {

    private val array = arrayListOf(
        R.drawable.banner1,
        R.drawable.banner2,
        R.drawable.banner3
    )
    private var Dt_Banner: Banner<Int, BannerImageAdapter<Int>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dt)



        supportActionBar?.hide()

        oo.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        oo.setOnClickListener { finish() }

        Dt_Banner = findViewById(R.id.dongtai_banner)

        Dt_Banner?.apply {
            setAdapter(object :BannerImageAdapter<Int>(array){
                override fun onBindView(p0: BannerImageHolder?, p1: Int?, p2: Int, p3: Int) {
                    Glide.with(p0!!.imageView).load(array[p2]).into(p0.imageView)
                }

            })
        }


    }
}