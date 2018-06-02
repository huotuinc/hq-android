package com.huotu.android.couponsleague.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.adapter.MessagePageAdapter
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.bean.MessageTypeEnum
import com.huotu.android.couponsleague.fragment.MessageFragment
import com.huotu.android.couponsleague.mvp.IPresenter
import kotlinx.android.synthetic.main.activity_message.*
import kotlinx.android.synthetic.main.layout_header2.*

class MessageActivity : BaseActivity<IPresenter>(),View.OnClickListener , ViewPager.OnPageChangeListener,TabLayout.OnTabSelectedListener {
    var messagePageAdapter:MessagePageAdapter?=null
    private var fragments=ArrayList<MessageFragment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        initView()
    }

    private fun initView(){

        header_title.text="消息动态"
        header_left_image.setOnClickListener(this)

        fragments.add(MessageFragment.newInstance(MessageTypeEnum.BusinessMessage.id))
        fragments.add(MessageFragment.newInstance(MessageTypeEnum.SystemMessage.id))
        var titles = ArrayList<String>()
        titles.add("业务通知")
        titles.add("系统通知")
        messagePageAdapter = MessagePageAdapter(supportFragmentManager , fragments, titles)

        message_viewPager.adapter = messagePageAdapter
        message_viewPager.addOnPageChangeListener(this)
        message_tablayout.setupWithViewPager(message_viewPager,true)

        message_tablayout.addOnTabSelectedListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.header_left_image->{
                finish()
            }
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if(tab!!.position==0){
            message_index1.visibility=View.VISIBLE
            message_index2.visibility = View.INVISIBLE
        }else{
            message_index1.visibility=View.INVISIBLE
            message_index2.visibility = View.VISIBLE
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        if(position==0){
            message_index1.visibility=View.VISIBLE
            message_index2.visibility = View.INVISIBLE
        }else{
            message_index1.visibility=View.INVISIBLE
            message_index2.visibility = View.VISIBLE
        }
    }
}
