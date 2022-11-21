package com.example.myapplication.ui.activity.Gov

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.GovTpyeList
import com.example.myapplication.network.NewsList
import com.example.myapplication.network.Servicecreate
import com.example.myapplication.ui.`fun`.EE
import kotlinx.android.synthetic.main.activity_g_o_v.*
import kotlinx.android.synthetic.main.activity_gov_type_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GovTypeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gov_type_list)


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        super.onResume()
        val id = intent.getIntExtra("gid",0)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        o.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        o.setOnClickListener { finish() }

        Servicecreate.smartcityService.getGovList(id).enqueue(object :Callback<GovTpyeList>{
            override fun onFailure(p0: Call<GovTpyeList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<GovTpyeList>, p1: Response<GovTpyeList>) {
                val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_gov_list,body.rows, GG::class.java)
                    rv_gov_list.layoutManager = LinearLayoutManager(this@GovTypeListActivity)
                    rv_gov_list.adapter  =adapter
                }
            }

        })

        sq.setOnClickListener {
            startActivity(Intent(this,App_GovActivity::class.java).apply {
                putExtra("id",id)
            })
        }
    }
}