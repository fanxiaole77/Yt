package com.example.myapplication.ui.activity.Bus

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.BusList
import com.example.myapplication.network.Servicecreate
import kotlinx.android.synthetic.main.activity_bus.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus)

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW
        s.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        s.setOnClickListener { finish() }

        Servicecreate.smartcityService.getBusList().enqueue(object :Callback<BusList>{
            override fun onFailure(p0: Call<BusList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<BusList>, p1: Response<BusList>) {
                val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_bus_list,body.rows,HH::class.java)
                    rv_bus_list.layoutManager = LinearLayoutManager(this@BusActivity)
                    rv_bus_list.adapter = adapter
                }
            }

        })

    }
}

class HH(view: View):ItemAdapter.MyViewHolder(view){
    val hao:TextView = view.findViewById(R.id.bus_jihao)
    val qidian:TextView = view.findViewById(R.id.bus_start)
    val zhongdian:TextView = view.findViewById(R.id.bus_end)
    val shijian:TextView = view.findViewById(R.id.bus_time)
    val moeny:TextView = view.findViewById(R.id.bus_money)
    val licheng:TextView = view.findViewById(R.id.bus_licheng)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        hao.text = (data[position] as BusList.Row).name
        qidian.text = (data[position] as BusList.Row).first
        zhongdian.text = (data[position] as BusList.Row).end
        shijian.text = (data[position] as BusList.Row).startTime
        moeny.text = (data[position] as BusList.Row).price.toString()
        licheng.text = (data[position] as BusList.Row).mileage

        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,BusContentActivity::class.java).apply {
                putExtra("bus_id",(data[position] as BusList.Row).id)
            })
        }
    }
}