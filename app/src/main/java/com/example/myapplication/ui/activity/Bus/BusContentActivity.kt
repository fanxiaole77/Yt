package com.example.myapplication.ui.activity.Bus

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.*
import kotlinx.android.synthetic.main.activity_bus_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusContentActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_content)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW
        f.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        f.setOnClickListener { finish() }

        val id = intent.getIntExtra("bus_id", 0)

        Servicecreate.smartcityService.getBusContent(id).enqueue(object : Callback<BusContent> {
            override fun onFailure(p0: Call<BusContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<BusContent>, p1: Response<BusContent>) {
                val body = p1.body()
                if (body != null) {
                    b_start.text = body.data.first
                    b_end.text = body.data.end
                    b_licheng.text = body.data.mileage
                    b_moenty.text = body.data.price.toString()

                    bus_save.setOnClickListener {
                        Servicecreate.smartcityService.postBus(Bus(body.data.first,body.data.end,body.data.price.toString(),"1")).enqueue(object :Callback<Message>{
                            override fun onFailure(p0: Call<Message>, p1: Throwable) {
                            }

                            override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                                val body = p1.body()
                                if (body != null){
                                    "成功".showToast()
                                }
                            }

                        })
                    }
                }
            }

        })

    }
}