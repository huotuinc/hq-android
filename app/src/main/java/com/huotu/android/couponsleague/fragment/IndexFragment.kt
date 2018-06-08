package com.huotu.android.couponsleague.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import cn.iwgang.countdownview.CountdownView
import com.facebook.drawee.view.SimpleDraweeView
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.activity.FavoriteActivity
import com.huotu.android.couponsleague.activity.MessageActivity
import com.huotu.android.couponsleague.activity.SearchActivity
import com.huotu.android.couponsleague.base.BaseFragment
import com.huotu.android.couponsleague.bean.KeyValue
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.utils.KeybordUtils
import com.huotu.android.couponsleague.utils.newIntent
import com.huotu.android.couponsleague.utils.newIntentForLogin
import com.huotu.android.couponsleague.utils.showToast
import com.huotu.android.couponsleague.widget.FrescoImageLoader
import com.huotu.android.couponsleague.widget.SelectDialog
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_index.*
import kotlinx.android.synthetic.main.layout_header.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [IndexFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [IndexFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class IndexFragment : BaseFragment<IPresenter>()
        , SwipeRefreshLayout.OnRefreshListener
        , View.OnClickListener {

    var category = ArrayList<String>()
    var fragments = ArrayList<BaseFragment<IPresenter>>()
    var fragmentAdapter : FragmentAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun initView() {
        header_search.setOnClickListener(this)
        header_right_image.setOnClickListener(this)
        header_left_lay.setOnClickListener(this)
        index_more.setOnClickListener(this)

        initFragments()

    }

    override fun fetchData() {

    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_index
    }


    override fun onRefresh() {

    }


    private fun initFragments(){
        category.clear()
        category.add("推荐")
        category.add("男装")
        category.add("女装")
        category.add("护肤")
        category.add("食品")
        category.add("百货")
        category.add("内衣袜子")
        category.add("数码")
        category.add("箱包配饰")
        category.add("家电")
        category.add("成人")
        category.add("家纺")
        category.add("运动")
        category.add("宠物")
        category.add("手机")

        fragments.clear()
        fragments.add(RecommandFragment.newInstance(category[0]))
        for(i in 1 until category.size){
            fragments.add( TabFragment.newInstance( category[i] ) )
        }

        fragmentAdapter = FragmentAdapter(this.childFragmentManager , fragments , category)
        index_viewPager.adapter = fragmentAdapter
        index_viewPager.offscreenPageLimit=3
        //main_viewPager.addOnPageChangeListener(this)
        index_tab.setupWithViewPager(index_viewPager,true)
        //main_tab.addOnTabSelectedListener(this)

    }


    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.header_search->{
                newIntent<SearchActivity>()
            }
            R.id.header_right_image->{
                newIntentForLogin<FavoriteActivity>()
            }
            R.id.header_left_lay->{
                selectPlat()
            }
            R.id.index_more->{
                showCategorys()
            }
        }
    }

    private fun showCategorys(){
        index_category.visibility = if( index_category.visibility == View.VISIBLE ) View.GONE else View.VISIBLE
    }

    private fun selectPlat(){
//        var dialog = SelectDialog(context!!)
//        var data = ArrayList<KeyValue>()
//        data.add(KeyValue(1,"拼多多"))
//        data.add(KeyValue(2,"京东"))
//        data.add(KeyValue(3,"天猫"))
//        dialog.show(header_left_lay , data , this)
        index_lay_platform.visibility= if( index_lay_platform.visibility == View.VISIBLE ) View.GONE else View.VISIBLE

        if(index_lay_platform.visibility == View.VISIBLE) {
            activity!!.window.attributes.alpha = 0.6f
        }else{
            activity!!.window.attributes.alpha=1f
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment IndexFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                IndexFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
