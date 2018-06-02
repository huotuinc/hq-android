package com.huotu.android.couponsleague.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.huotu.android.couponsleague.base.BaseFragment
import com.huotu.android.couponsleague.mvp.IPresenter

class MessagePageAdapter(fragmentManager : FragmentManager, var fragments : List<BaseFragment<IPresenter>> ,
                         var titles :ArrayList<String>) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view.equals( `object`)
//    }

    override fun getCount(): Int {
        return fragments.size
    }
}