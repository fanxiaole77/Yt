package com.example.myapplication.ui.home

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.NewsList
import com.example.myapplication.network.Servicecreate
import kotlinx.android.synthetic.main.activity_sv.*
import kotlinx.android.synthetic.main.fragment_news_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SvActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sv)
        supportActionBar?.hide()
        window.statusBarColor =Color.YELLOW
        w.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        w.setOnClickListener { finish() }

        sear.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                Servicecreate.smartcityService.getNewList(newText,null).enqueue(object:Callback<NewsList>{
                    override fun onFailure(p0: Call<NewsList>, p1: Throwable) {
                    }

                    override fun onResponse(p0: Call<NewsList>, p1: Response<NewsList>) {
                      val body =p1.body()
                        if (body != null){
                            val adapter = ItemAdapter(R.layout.item_news_list,body.rows,BB::class.java)
                            rv_sv.layoutManager = LinearLayoutManager(this@SvActivity)
                            rv_sv.adapter = adapter
                        }
                    }

                })

                return false
            }
        })
    }
}