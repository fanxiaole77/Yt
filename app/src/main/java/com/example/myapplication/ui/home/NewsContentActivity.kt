package com.example.myapplication.ui.home

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.extensions.showToast
import com.example.myapplication.network.*
import kotlinx.android.synthetic.main.activity_news_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class NewsContentActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)

        val id = intent.getIntExtra("new_id",0)
        val bannerid = intent.getIntExtra("bannerid",0)
        supportActionBar?.hide()
        window.statusBarColor = Color.YELLOW
        q.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        q.setOnClickListener { finish() }

        Servicecreate.smartcityService.getNewsContent(id).enqueue(object :Callback<NewContent>{
            override fun onFailure(p0: Call<NewContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewContent>, p1: Response<NewContent>) {
              val body = p1.body()
                if (body != null){
                    news_title.text = body.data.title
                    news_content.text = Html.fromHtml(body.data.content)
                    new_like.text = body.data.likeNum.toString()
                    Glide.with(this@NewsContentActivity).load(Servicecreate.url + body.data.cover).into(news_image)
                }
            }

        })


        fun aa() = Servicecreate.smartcityService.getNewsComment(id).enqueue(object:Callback<NewsComment>{
            override fun onFailure(p0: Call<NewsComment>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewsComment>, p1: Response<NewsComment>) {
                val body = p1.body()
                if (body != null){
                    val  adapter = ItemAdapter(R.layout.item_news_comment,body.rows,YY::class.java)
                    rv_comment.layoutManager = LinearLayoutManager(this@NewsContentActivity)
                    rv_comment.adapter = adapter
                }
            }

        })

        aa()

        btn_comment.setOnClickListener {
            val content = et_comment.text.toString()
            Servicecreate.smartcityService.postComment(Comment(content,id)).enqueue(object :Callback<Message>{
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

class YY(view: View):ItemAdapter.MyViewHolder(view){
    val user:TextView = view.findViewById(R.id.com_user)
    val content:TextView = view.findViewById(R.id.com_content)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        user.text = (data[position] as NewsComment.Row).userName
        content.text = (data[position] as NewsComment.Row).content
    }
}