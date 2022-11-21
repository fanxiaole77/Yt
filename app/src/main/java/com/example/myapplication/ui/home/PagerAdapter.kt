package com.example.myapplication.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.network.NewsList
import com.example.myapplication.network.NewsType

class PagerAdapter(fm:FragmentManager,val list: List<NewsType.Data>):FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return NewsListFragment.newInstance(list[position].id.toString(),"")
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].name
    }


}