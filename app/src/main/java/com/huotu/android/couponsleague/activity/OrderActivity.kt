package com.huotu.android.couponsleague.activity


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.R.id.header_right_image
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.base.BaseFragment
import com.huotu.android.couponsleague.bean.OrderStatusEnum
import com.huotu.android.couponsleague.bean.OrderTypeEnum
import com.huotu.android.couponsleague.fragment.FragmentAdapter
import com.huotu.android.couponsleague.fragment.OrderFragment
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.utils.showToast
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.layout_header2.*

class OrderActivity : BaseActivity<IPresenter>(),TabLayout.OnTabSelectedListener ,View.OnClickListener{
    var orderAdapter:FragmentAdapter?=null
    var fragments=ArrayList<BaseFragment<IPresenter>>()
    var titles =ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initView()
    }
    private fun initView(){
        header_title.text="推广订单"
        header_left_image.setOnClickListener(this)
        header_right_image.setImageResource(R.mipmap.help)
        header_right_image.setOnClickListener(this)
        fragments.clear()
        titles.clear()
        titles.add(OrderStatusEnum.ALL.desc)
        fragments.add(OrderFragment.newInstance(OrderTypeEnum.PINDUODUO.id,OrderStatusEnum.ALL.id))
        titles.add(OrderStatusEnum.PREPARE.desc)
        fragments.add(OrderFragment.newInstance(OrderTypeEnum.PINDUODUO.id,OrderStatusEnum.PREPARE.id))
        titles.add(OrderStatusEnum.RECEIVED.desc)
        fragments.add(OrderFragment.newInstance(OrderTypeEnum.PINDUODUO.id,OrderStatusEnum.RECEIVED.id))
        titles.add(OrderStatusEnum.INVAIDAD.desc)
        fragments.add(OrderFragment.newInstance(OrderTypeEnum.PINDUODUO.id,OrderStatusEnum.INVAIDAD.id))
        titles.add(OrderStatusEnum.ARRIVED.desc)
        fragments.add(OrderFragment.newInstance(OrderTypeEnum.PINDUODUO.id,OrderStatusEnum.ARRIVED.id))
        orderAdapter = FragmentAdapter(supportFragmentManager,fragments,titles)
        order_viewPager.adapter=orderAdapter

        order_tablayout.setupWithViewPager(order_viewPager,true)

        order_tablayout.addOnTabSelectedListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.header_left_image->{finish()}
            R.id.header_right_image->{
                //todo
                showToast("help")
            }
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {

    }
}
