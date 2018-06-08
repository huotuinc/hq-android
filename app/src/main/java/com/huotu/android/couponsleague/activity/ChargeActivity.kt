package com.huotu.android.couponsleague.activity

import android.os.Bundle
import android.view.View
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.utils.newIntent
import com.huotu.android.couponsleague.utils.showToast
import kotlinx.android.synthetic.main.activity_charge.*
import kotlinx.android.synthetic.main.layout_header2.*

class ChargeActivity : BaseActivity<IPresenter>(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charge)

        header_title.text="兑换"
        header_left_image.setOnClickListener(this)

        charge_charge.setOnClickListener(this)
        charge_lay_pay.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.header_left_image->{
                finish()
            }
            R.id.charge_lay_pay->{
                newIntent<AlipayActivity>()
            }
            R.id.charge_charge->{
                showToast("charge")
            }
        }
    }
}
