package com.huotu.android.couponsleague.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.adapter.FavoriteAdapter
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.bean.ApiResult
import com.huotu.android.couponsleague.bean.FavoriteBean
import com.huotu.android.couponsleague.mvp.contract.FavoriteContract
import com.huotu.android.couponsleague.mvp.presenter.FavoritePresenter
import com.huotu.android.couponsleague.widget.RecycleItemDivider
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.layout_header2.*

class FavoriteActivity : BaseActivity<FavoriteContract.Presenter>()
        ,FavoriteContract.View
        ,View.OnClickListener
        ,SwipeRefreshLayout.OnRefreshListener
        ,BaseQuickAdapter.RequestLoadMoreListener
        ,BaseQuickAdapter.OnItemChildClickListener {
    var favoriteAdapter:FavoriteAdapter?=null
    var data= ArrayList<FavoriteBean>()
    var selectedAll=false
    var presenter:FavoritePresenter?=null
    var pageIndex=1L


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
        favoriteAdapter!!.setOnLoadMoreListener(this , favorite_recyclerview)
        favoriteAdapter!!.onItemChildClickListener =this
        favorite_recyclerview.layoutManager=LinearLayoutManager(this)
        favorite_recyclerview.addItemDecoration(RecycleItemDivider(this,LinearLayoutManager.VERTICAL,1))
        favorite_recyclerview.adapter = favoriteAdapter
        favorite_refreshview.setOnRefreshListener(this)

        for(i in 0..10){
            data.add(FavoriteBean(1, false , 0 ,"测试" ,"", "","", "http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg" ,null,"","","","","",""))
        }
        favoriteAdapter!!.notifyDataSetChanged()

        presenter=FavoritePresenter(this)
        presenter!!.favoriteList(pageIndex)
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

        selectedAll=true
        for(bean in data){
            if(!bean.selected){
                selectedAll=false
                break
            }
        }

        favorite_select.setCompoundDrawables( if(selectedAll) ContextCompat.getDrawable(this , R.mipmap.selected) else ContextCompat.getDrawable(this , R.mipmap.unselected) , null,null,null )

    }

    override fun onRefresh() {
        pageIndex=1
        presenter!!.favoriteList(pageIndex)
    }

    override fun showProgress(msg: String) {
        favorite_loading.visibility=View.VISIBLE
        super.showProgress(msg)
    }

    override fun hideProgress() {
        favorite_loading.visibility=View.GONE
        super.hideProgress()
    }

    override fun onLoadMoreRequested() {
        presenter!!.favoriteList(pageIndex+1)
    }

    fun delete(){

    }

    private fun select(){
        selectedAll=!selectedAll
        favorite_select.setCompoundDrawables( if(selectedAll) ContextCompat.getDrawable(this , R.mipmap.selected) else ContextCompat.getDrawable(this , R.mipmap.unselected) , null,null,null )

        for ( bean in data){
            bean.selected=!bean.selected
        }
        favoriteAdapter!!.notifyDataSetChanged()
    }
    fun share(){

    }

    override fun favoriteCallback(apiResult: ApiResult<Any>) {

    }

    override fun favoriteListCallback(apiResult: ApiResult<FavoriteBean>) {
        favorite_refreshview.isRefreshing=false
    }
}
