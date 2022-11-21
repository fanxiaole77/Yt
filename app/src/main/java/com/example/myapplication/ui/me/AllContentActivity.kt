package com.example.myapplication.ui.me

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.network.All
import com.example.myapplication.network.Servicecreate
import kotlinx.android.synthetic.main.activity_all_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllContentActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_content)

        u.setOnClickListener { finish() }
        u.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)

        window.statusBarColor = Color.YELLOW
        supportActionBar?.hide()
        val id = intent.getIntExtra("allid",0)
        Servicecreate.smartcityService.getAll().enqueue(object :Callback<All>{
            override fun onFailure(p0: Call<All>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<All>, p1: Response<All>) {
               val body =p1.body()
                if (body != null){
                    for (a in body.rows){
                        if (id == a.id){
                            start.text = a.start
                            end.text = a.end
                            a_user.text = a.userName
                            a_phone.text = a.userTel

                        }
                    }
                }
            }

        })

    }
}