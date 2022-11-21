package com.example.myapplication.ui.activity.Gov

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.App
import com.example.myapplication.network.Message
import com.example.myapplication.network.Servicecreate
import kotlinx.android.synthetic.main.activity_app__gov.*
import kotlinx.android.synthetic.main.activity_gov_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class App_GovActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app__gov)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        a.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        a.setOnClickListener { finish() }

        val id = intent.getIntExtra("id",0)

        save.setOnClickListener {
            val title = gv_title.text.toString()
            val content = gv_content.text.toString()
            Servicecreate.smartcityService.postApp(App(id,content,title)).enqueue(object :Callback<Message>{
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