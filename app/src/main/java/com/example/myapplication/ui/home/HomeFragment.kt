package com.example.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.*
import com.example.myapplication.ui.activity.Dj.DjActivity
import com.example.myapplication.ui.activity.Gov.GOVActivity
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Save.setOnClickListener {
            startActivity(Intent(activity,SvActivity::class.java))
        }
        dj.setOnClickListener {
            startActivity(Intent(activity,DjActivity::class.java))
        }

        Servicecreate.smartcityService.getHomeBanner().enqueue(object :Callback<HomeBanner>{
            override fun onFailure(p0: Call<HomeBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HomeBanner>, p1: Response<HomeBanner>) {
               val body = p1.body()
                if (body != null){
                    home_banner.adapter = object :BannerImageAdapter<HomeBanner.Row>(body.rows){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: HomeBanner.Row?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(p0!!.imageView).load(Servicecreate.url + p1!!.advImg).into(p0!!.imageView)
                        }

                    }
                    home_banner.setOnBannerListener(object:OnBannerListener<HomeBanner.Row>{
                        override fun OnBannerClick(p0: HomeBanner.Row?, p1: Int) {
                            startActivity(Intent(this@HomeFragment.requireActivity(),NewsContentActivity::class.java).apply {
                                putExtra("new_id",p0!!.targetId)
                            })
                        }

                    })
                }
            }
        })

        Servicecreate.smartcityService.getService(null,null).enqueue(object :Callback<HomeService>{
            override fun onFailure(p0: Call<HomeService>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HomeService>, p1: Response<HomeService>) {
               val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_service,body.rows,AA::class.java,8,
                        listOf(activity))
                    rv_service.layoutManager = GridLayoutManager(this@HomeFragment.requireActivity(),4)
                    rv_service.adapter = adapter
                }
            }

        })

        Servicecreate.smartcityService.getNewsType().enqueue(object :Callback<NewsType>{
            override fun onFailure(p0: Call<NewsType>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewsType>, p1: Response<NewsType>) {
               val body = p1.body()
                if (body != null){
                    val adapter = PagerAdapter(this@HomeFragment.requireFragmentManager(),body.data)
                    v1.adapter = adapter
                    t1.setupWithViewPager(v1)
                }
            }

        })

    }

}

class AA(view: View):ItemAdapter.MyViewHolder(view){
    val name:TextView = view.findViewById(R.id.s_text)
    val image:ImageView = view.findViewById(R.id.s_image)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as HomeService.Row).serviceName
        Glide.with(image).load(Servicecreate.url + (data[position] as HomeService.Row).imgUrl).into(image)

        if (position == 7){
            name.text = "更多服务"
            Glide.with(image).load(Servicecreate.url + (data[position] as HomeService.Row).imgUrl).into(image)
            val aa = list[0] as MainActivity
            itemView.setOnClickListener {
               aa.bottom11.selectedItemId = R.id.nav_dun
            }
        }

        when ((data[position] as HomeService.Row).serviceName) {
            "教育" -> {
                itemView.setOnClickListener {
                    name.context.startActivity(Intent(name.context, GOVActivity::class.java))
                }
            }


        }

    }

}