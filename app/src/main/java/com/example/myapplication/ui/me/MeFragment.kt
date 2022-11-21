package com.example.myapplication.ui.me

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.network.All
import com.example.myapplication.network.Servicecreate
import com.example.myapplication.network.UserInfo
import kotlinx.android.synthetic.main.fragment_me.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_user.setOnClickListener {
            startActivity(Intent(activity,UserActivity::class.java))
        }
        btn_pass.setOnClickListener {
            startActivity(Intent(activity,PassActivity::class.java))
        }
        btn_feed.setOnClickListener {
            startActivity(Intent(activity,FeedActivity::class.java))
        }
        btn_All.setOnClickListener {
            startActivity(Intent(activity,AllActivity::class.java))
        }

        Servicecreate.smartcityService.getUserInfo().enqueue(object :Callback<UserInfo>{
            override fun onFailure(p0: Call<UserInfo>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<UserInfo>, p1: Response<UserInfo>) {
                val body = p1.body()
                if (body != null){
                    user.text = body.user.nickName
                }
            }

        })

    }


}