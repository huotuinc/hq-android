package com.huotu.android.couponsleague.activity

import android.os.Bundle
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.mvp.IPresenter

class AlipayActivity : BaseActivity<IPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alipay)
    }
}
