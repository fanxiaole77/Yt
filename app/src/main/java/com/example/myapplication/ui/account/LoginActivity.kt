package com.example.myapplication.ui.account

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.extensions.edit
import com.example.myapplication.extensions.sharedPreferences
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.Login
import com.example.myapplication.network.Message
import com.example.myapplication.network.Servicecreate
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
        window.statusBarColor = Color.WHITE

        go_register.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        login.setOnClickListener {
            val user = et_user.text.toString()
            val pass = et_pass.text.toString()
            Servicecreate.smartcityService.postLogin(Login(pass,user)).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                    Log.i("aaa","${Servicecreate.url}",p1)
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                    val body = p1.body()
                    if (body != null){
                        if (body.code == 200){
                            body.msg.showToast()
                            sharedPreferences.edit {
                                putString("token",body.token)
                            }
                            Log.d("aaa",body.msg)
                            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        }else{
                            body.msg.showToast()
                        }
                    }
                }

            })
        }

    }
}