package com.example.myapplication.ui.`fun`

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.SmartCiryApplication
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.HomeService
import com.example.myapplication.network.Servicecreate
import com.example.myapplication.ui.activity.Bus.BusActivity
import com.example.myapplication.ui.activity.Gov.GOVActivity
import kotlinx.android.synthetic.main.fragment_fun.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FunFragment : Fragment() {

    lateinit var Rvaa:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fun, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Servicecreate.smartcityService.getService(null,null).enqueue(object : Callback<HomeService> {
            override fun onFailure(p0: Call<HomeService>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HomeService>, p1: Response<HomeService>) {
                val body = p1.body()
                if (body != null) {
                    val adapter = ItemAdapter(R.layout.item_service, body.rows, EE::class.java)
                    rv_fun.layoutManager = GridLayoutManager(this@FunFragment.requireActivity(), 3)
                    rv_fun.adapter = adapter
                }
            }

        })

        Servicecreate.smartcityService.getService(null,null).enqueue(object :Callback<HomeService>{
            override fun onFailure(p0: Call<HomeService>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HomeService>, p1: Response<HomeService>) {
               val body = p1.body()
                val rv = view!!.findViewById<RecyclerView>(R.id.rv_fun)
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_fun_name,body.rows,JK::class.java,3,
                        listOf(rv))
                    rv_fun_type.layoutManager = LinearLayoutManager(this@FunFragment.requireActivity())
                    rv_fun_type.adapter = adapter
                }
            }

        })

    }



}

class EE(view: View) : ItemAdapter.MyViewHolder(view) {
    val name1 = view.findViewById<TextView>(R.id.s_text)
    val image1 = view.findViewById<ImageView>(R.id.s_image)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name1.text = (data[position] as HomeService.Row).serviceName
        Glide.with(image1).load(Servicecreate.url + (data[position] as HomeService.Row).imgUrl)
            .into(image1)

        when ((data[position] as HomeService.Row).serviceName) {
            "政府服务热线" -> {
                itemView.setOnClickListener {
                    name1.context.startActivity(Intent(name1.context, GOVActivity::class.java))
                }
            }

            "智慧巴士" -> {
                itemView.setOnClickListener {
                    name1.context.startActivity(Intent(name1.context, BusActivity::class.java))
                }
            }
        }

    }
}

class JK(val view: View):ItemAdapter.MyViewHolder(view){

    val type:TextView = view.findViewById(R.id.fun_name)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
       type.text = (data[position] as HomeService.Row).serviceType
        val type = list[0] as RecyclerView
        itemView.setOnClickListener {
            Servicecreate.smartcityService.getService((data[position] as HomeService.Row).serviceType,null).enqueue(object :Callback<HomeService>{
                override fun onFailure(p0: Call<HomeService>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<HomeService>, p1: Response<HomeService>) {
                    val body = p1.body()
                    if (body != null) {
                        val adapter = ItemAdapter(R.layout.item_service,body.rows,EE::class.java)
                        type.layoutManager = GridLayoutManager(view.context,3)
                        type.adapter = adapter
                    }
                }

            })
        }
    }

}