package com.example.myapplication.ui.activity.Gov

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.network.GovContent
import com.example.myapplication.network.Servicecreate
import kotlinx.android.synthetic.main.activity_gov_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GovContentActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gov_content)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        p.setOnClickListener { finish() }
        p.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)

        val id = intent.getIntExtra("ggid",0)

        Log.d("ggid",id.toString())

        Servicecreate.smartcityService.getGovContent(id).enqueue(object :Callback<GovContent>{
            override fun onFailure(p0: Call<GovContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<GovContent>, p1: Response<GovContent>) {
                val body = p1.body()
                if (body != null){
                    gg_title.text = body.data.title
                    gg_content.text = body.data.content
                    gg_danwei.text = body.data.undertaker
                    gg_jieguo.text = body.data.detailResult
                    gg_shijian.text = body.data.createTime
                    gg_zhuangtai.text = if (body.data.state == "1"){
                        "已处理"
                    }else{
                        "未处理"
                    }
                    Glide.with(this@GovContentActivity).load(Servicecreate.url + body.data.imgUrl).into(gg_image)
                }
            }

        })


    }
}