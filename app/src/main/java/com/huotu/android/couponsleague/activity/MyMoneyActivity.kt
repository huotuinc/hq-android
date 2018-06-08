package com.huotu.android.couponsleague.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.adapter.MoneyAdapter
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.bean.MoneyBean
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.utils.newIntent
import com.huotu.android.couponsleague.widget.RecycleItemDivider
import kotlinx.android.synthetic.main.activity_my_money.*
import kotlinx.android.synthetic.main.layout_header2.*
import java.util.*

/**
 * 我的余额明细
 */
class MyMoneyActivity : BaseActivity<IPresenter>() ,View.OnClickListener{

    var moneyAdapter:MoneyAdapter?=null
    var data=ArrayList<MoneyBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_money)

        header_title.text="余额明细"
        header_left_image.setOnClickListener(this)
        money_charge.setOnClickListener(this)

        data.add(MoneyBean(""))
        data.add(MoneyBean(""))
        data.add(MoneyBean(""))

        moneyAdapter= MoneyAdapter(data)
        money_recyclerView.layoutManager=LinearLayoutManager(this)
        money_recyclerView.adapter = moneyAdapter
        money_recyclerView.addItemDecoration(RecycleItemDivider(this,LinearLayoutManager.VERTICAL,1,R.color.line_color2))
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.header_left_image->{
                finish()
            }
            R.id.money_charge->{
                newIntent<ChargeActivity>()
            }
        }
    }
}
