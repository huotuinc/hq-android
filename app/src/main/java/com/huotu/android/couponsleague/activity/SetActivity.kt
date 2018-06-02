package com.huotu.android.couponsleague.activity


import android.os.Bundle
import android.view.View
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.mvp.IPresenter
import kotlinx.android.synthetic.main.layout_header2.*

class SetActivity : BaseActivity<IPresenter>(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set)

        header_title.text="设置"
        header_left_image.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.header_left_image->{
                finish()
            }
        }
    }
}
