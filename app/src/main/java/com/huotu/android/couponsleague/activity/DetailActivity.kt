package com.huotu.android.couponsleague.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.adapter.DetailAdapter
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.bean.DetailBean
import com.huotu.android.couponsleague.mvp.IPresenter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity<IPresenter>(),View.OnClickListener {
    var detailAdapter:DetailAdapter?=null
    var data=ArrayList<DetailBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initView()

    }

    private fun initView(){

        header_left_image.setOnClickListener(this)

        detail_image.setImageURI("http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg")

        for(i in 0 .. 10){
            data.add(DetailBean(i,0,"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg"))
        }
        data.add(DetailBean(4,0,"http://t00img.yangkeduo.com/t09img/images/2018-05-28/4ac0853e1a7a898315f5155bdb733dff.jpeg"))

        data.add(DetailBean(4,0,"http://t04img.yangkeduo.com/images/2018-05-26/3308bf00afb37922ceef70b9991e0dfd.jpeg"))

        data.add(DetailBean(4,0,"http://t08img.yangkeduo.com/images/2018-04-27/facf8f2067f128b3b2af353411c3ffcd.jpeg"))


        detail_recyclerView.layoutManager=LinearLayoutManager(this)
        detailAdapter=DetailAdapter(data)

        var top = LayoutInflater.from(this).inflate(R.layout.layout_detail_top, null)
        detailAdapter!!.removeAllHeaderView()
        detailAdapter!!.addHeaderView(top)

        detail_recyclerView.adapter = detailAdapter
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.header_left_image->{
                finish()
            }
        }
    }
}
