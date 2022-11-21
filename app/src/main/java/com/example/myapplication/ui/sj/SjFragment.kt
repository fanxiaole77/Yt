package com.example.myapplication.ui.sj

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.network.NewsList
import com.example.myapplication.network.Servicecreate
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.fragment_sj.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SjFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sj, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Servicecreate.smartcityService.getNewList(null,null).enqueue(object : Callback<NewsList> {
            override fun onFailure(p0: Call<NewsList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewsList>, p1: Response<NewsList>) {
                val body = p1.body()
                val X = mBarchbar.xAxis
                if (body != null){
                    var list1 = mutableListOf<String>()
                    for (a in 0 until 5){
                        list1.add(body.rows[a].title)
                    }
                    X.valueFormatter = IndexAxisValueFormatter(list1)

                    var list = mutableListOf<BarEntry>()
                    for (a in 0 until 5){
                        list.add(BarEntry(a.toFloat(),body.rows[a].likeNum.toFloat()))
                    }
                    val aa = BarDataSet(list,"")
                    val bb = BarData(aa)
                    mBarchbar.data = bb

                }
            }

        })
    }


}