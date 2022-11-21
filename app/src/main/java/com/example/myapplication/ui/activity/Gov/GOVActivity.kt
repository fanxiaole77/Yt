package com.example.myapplication.ui.activity.Gov

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.GovBanner
import com.example.myapplication.network.GovTpyeList
import com.example.myapplication.network.GovType
import com.example.myapplication.network.Servicecreate
import com.example.myapplication.ui.`fun`.EE
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.activity_g_o_v.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GOVActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_g_o_v)


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        super.onResume()

        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW

        i.setOnClickListener { finish() }
        i.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)

        Servicecreate.smartcityService.getGovBanner().enqueue(object :Callback<GovBanner>{
            override fun onFailure(p0: Call<GovBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<GovBanner>, p1: Response<GovBanner>) {
                val body =p1.body()
                if (body != null){
                    gov_banner.adapter = object :BannerImageAdapter<GovBanner.Data>(body.data){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: GovBanner.Data?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(p0!!.imageView).load(Servicecreate.url + p1!!.imgUrl).into(p0!!.imageView)
                        }

                    }
                }
            }

        })

        Servicecreate.smartcityService.getGovType().enqueue(object :Callback<GovType>{
            override fun onFailure(p0: Call<GovType>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<GovType>, p1: Response<GovType>) {
                val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_gov_type,body.rows,FF::class.java,8)
                    rv_gov_type.layoutManager = GridLayoutManager(this@GOVActivity,4)
                    rv_gov_type.adapter = adapter
                }
            }

        })

        Servicecreate.smartcityService.getMyGovList().enqueue(object :Callback<GovTpyeList>{
            override fun onFailure(p0: Call<GovTpyeList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<GovTpyeList>, p1: Response<GovTpyeList>) {
                val body =  p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_gov_list,body.rows,GG::class.java)
                    rv_gov_my_list.layoutManager = LinearLayoutManager(this@GOVActivity)
                    rv_gov_my_list.adapter  =adapter
                }
            }


        })
    }
}
class FF(view: View):ItemAdapter.MyViewHolder(view){
    val name:TextView = view.findViewById(R.id.gov_text)
    val image:ImageView = view.findViewById(R.id.gov_image)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as GovType.Row).name
        Glide.with(image).load(Servicecreate.url + (data[position] as GovType.Row).imgUrl).into(image)

        if (position == 7){
            name.text = "其他诉求"
            Glide.with(image).load(Servicecreate.url + (data[position] as GovType.Row).imgUrl).into(image)
        }

        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,GovTypeListActivity::class.java).apply {
                putExtra("gid",(data[position] as GovType.Row).id)
            })
        }
    }
}

class GG(view: View):ItemAdapter.MyViewHolder(view){
    val title:TextView = view.findViewById(R.id.g_title)
    val danwei:TextView = view.findViewById(R.id.g_danwei)
    val shijian:TextView = view.findViewById(R.id.g_shijian)
    val zhuangtai:TextView = view.findViewById(R.id.g_chuli)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        title.text = (data[position] as GovTpyeList.Row).title
        danwei.text = (data[position] as GovTpyeList.Row).undertaker
        shijian.text = (data[position] as GovTpyeList.Row).createTime
        zhuangtai.text = if ((data[position] as GovTpyeList.Row).state == "1"){
            "已处理"
        }else{
            "未处理"
        }

        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,GovContentActivity::class.java).apply {
                putExtra("ggid",(data[position] as GovTpyeList.Row).id)
            })
        }
    }
}