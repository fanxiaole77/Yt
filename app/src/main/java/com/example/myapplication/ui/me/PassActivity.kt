package com.example.myapplication.ui.me

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.Message
import com.example.myapplication.network.Pass
import com.example.myapplication.network.Servicecreate
import kotlinx.android.synthetic.main.activity_pass.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PassActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        r.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        r.setOnClickListener { finish() }

        btn_pass1.setOnClickListener {
            val news = new_pass.text.toString()
            val orld = orld_pass.text.toString()
            Servicecreate.smartcityService.putPass(Pass(news,orld)).enqueue(object :Callback<Message>{
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