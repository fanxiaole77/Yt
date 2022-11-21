package com.example.myapplication.ui.me

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.Message
import com.example.myapplication.network.Servicecreate
import com.example.myapplication.network.User
import com.example.myapplication.network.UserInfo
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.fragment_me.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        window.statusBarColor = Color.YELLOW
        supportActionBar?.hide()
        e.setOnClickListener { finish() }
        e.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)

        var sex = "0"
        rg_sex11.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.man11){
                sex = "0"
            }else{
                sex = "1"
            }
        }

        Servicecreate.smartcityService.getUserInfo().enqueue(object :Callback<UserInfo>{
            override fun onFailure(p0: Call<UserInfo>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<UserInfo>, p1: Response<UserInfo>) {
                val body = p1.body()
                if (body != null){
                    et_phone11.setText(body.user.phonenumber)
                    et_nick11.setText(body.user.nickName)
                    if (body.user.sex == "1"){
                        wu_man11.isChecked = true
                    }else{
                        man11.isChecked = true
                    }
                }
            }
        })

        btn_user1.setOnClickListener {
            val nick = et_nick11.text.toString()
            val phone = et_phone11.text.toString()
            Servicecreate.smartcityService.putUser(User(nick,phone,sex)).enqueue(object :Callback<Message>{
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