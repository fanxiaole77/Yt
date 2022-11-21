package com.example.myapplication.ui.me

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.All
import com.example.myapplication.network.Servicecreate
import kotlinx.android.synthetic.main.activity_all.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW
        y.setOnClickListener { finish() }
        y.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)

        btn2.setOnClickListener {
            Servicecreate.smartcityService.getAll().enqueue(object :Callback<All>{
                override fun onFailure(p0: Call<All>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<All>, p1: Response<All>) {
                    val body =p1.body()
                    if (body != null){
                        val adapter = ItemAdapter(R.layout.item_all,body.rows,DD::class.java)
                        rv_all.layoutManager = LinearLayoutManager(this@AllActivity)
                        rv_all.adapter = adapter
                    }
                }

            })
        }

        btn1.setOnClickListener {
            Servicecreate.smartcityService.getAll().enqueue(object :Callback<All>{
                override fun onFailure(p0: Call<All>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<All>, p1: Response<All>) {
                    val body =p1.body()
                    if (body != null){
                        val adapter = ItemAdapter(R.layout.item_all,body.rows,DD::class.java)
                        rv_all.layoutManager = LinearLayoutManager(this@AllActivity)
                        rv_all.adapter = adapter
                    }
                }

            })
        }

    }
}

class DD(view: View):ItemAdapter.MyViewHolder(view){
    val danhao = view.findViewById<TextView>(R.id.danhao)
    val leixing = view.findViewById<TextView>(R.id.lexing)
    val shijian = view.findViewById<TextView>(R.id.riqi)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        danhao.text = (data[position] as All.Row).orderNum
        leixing.text = if ((data[position] as All.Row).paymentType == null){
            "未支付"
        }else{
            "已支付"
        }
//        shijian.text = (data[position] as All.Row).payTime.toString()

        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,AllContentActivity::class.java).apply {
                putExtra("allid",(data[position] as All.Row).id)
            })
        }
    }
}