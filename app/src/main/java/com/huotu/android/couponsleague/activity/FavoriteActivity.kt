package com.huotu.android.couponsleague.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.adapter.FavoriteAdapter
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.bean.FavoriteBean
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.widget.RecycleItemDivider
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.layout_header2.*

class FavoriteActivity : BaseActivity<IPresenter>(),View.OnClickListener,BaseQuickAdapter.OnItemChildClickListener {
    var favoriteAdapter:FavoriteAdapter?=null
    var data= ArrayList<FavoriteBean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        initView()
    }

    private fun initView(){
        header_title.text="收藏夹"
        header_right_text.text="批量删除"
        header_right_text.setOnClickListener(this)
        header_left_image.setOnClickListener(this)
        favorite_select.setOnClickListener(this)
        favorite_share.setOnClickListener(this)

        favoriteAdapter = FavoriteAdapter(data)
        favoriteAdapter!!.onItemChildClickListener =this
        favorite_recyclerview.layoutManager=LinearLayoutManager(this)
        favorite_recyclerview.addItemDecoration(RecycleItemDivider(this,LinearLayoutManager.VERTICAL,1))
        favorite_recyclerview.adapter = favoriteAdapter

        for(i in 0..10){
            data.add(FavoriteBean(1, "http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg" ,""))
        }
        favoriteAdapter!!.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.header_right_text->{
                delete()
            }
            R.id.header_left_image->{
                finish()
            }
            R.id.favorite_select->{
                select()
            }
            R.id.favorite_share->{
                share()
            }
        }
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        (adapter!!.getItem(position) as FavoriteBean).selected=  !(adapter!!.getItem(position) as FavoriteBean).selected
        adapter.notifyItemChanged(position)
    }

    fun delete(){

    }
    fun select(){

    }
    fun share(){

    }
}
