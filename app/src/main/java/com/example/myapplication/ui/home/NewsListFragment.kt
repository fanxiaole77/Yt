package com.example.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.extensions.ItemAdapter
import com.example.myapplication.network.NewsList
import com.example.myapplication.network.Servicecreate
import kotlinx.android.synthetic.main.fragment_news_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Servicecreate.smartcityService.getNewList(null,param1).enqueue(object :Callback<NewsList>{
            override fun onFailure(p0: Call<NewsList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewsList>, p1: Response<NewsList>) {
                val body =p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_news_list,body.rows,BB::class.java)
                    rv_news_list.layoutManager = LinearLayoutManager(this@NewsListFragment.requireActivity())
                    rv_news_list.adapter = adapter
                }
            }

        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
class BB(view: View):ItemAdapter.MyViewHolder(view){

    val title = view.findViewById<TextView>(R.id.n_title)
    val content = view.findViewById<TextView>(R.id.n_content)
    val time = view.findViewById<TextView>(R.id.n_time)
    val iknu = view.findViewById<TextView>(R.id.n_iknu)
    val image = view.findViewById<ImageView>(R.id.n_image)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        title.text = (data[position] as NewsList.Row).title
        content.text = Html.fromHtml((data[position] as NewsList.Row).content)
        time.text = (data[position] as NewsList.Row).publishDate
        iknu.text = (data[position] as NewsList.Row).likeNum.toString()
        Glide.with(image).load(Servicecreate.url + (data[position] as NewsList.Row).cover).into(image)
        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,NewsContentActivity::class.java).apply {
                putExtra("new_id",(data[position] as NewsList.Row).id)
            })
        }
    }

}