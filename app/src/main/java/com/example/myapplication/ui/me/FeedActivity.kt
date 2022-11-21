package com.example.myapplication.ui.me

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.Feed
import com.example.myapplication.network.Message
import com.example.myapplication.network.Servicecreate
import kotlinx.android.synthetic.main.activity_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        t.setOnClickListener { finish() }
        t.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)

        btn_feed1.setOnClickListener {
            val title = feed_title.text.toString()
            val content = feed_content.text.toString()
            Servicecreate.smartcityService.postFeed(Feed(content,title)).enqueue(object:Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                  val body = p1.body()
                    if (body != null){
                        if (body.code == 200){
                            body.msg.showToast()
                        }else{
                            body.msg.showToast()
                        }
                    }
                }

            })
        }

    }
}