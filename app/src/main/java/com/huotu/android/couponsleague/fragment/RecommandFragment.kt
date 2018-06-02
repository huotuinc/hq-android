package com.huotu.android.couponsleague.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import cn.iwgang.countdownview.CountdownView
import com.facebook.drawee.view.SimpleDraweeView

import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.activity.DetailActivity
import com.huotu.android.couponsleague.adapter.DataAdapter
import com.huotu.android.couponsleague.adapter.HorizontalBannerAdapter
import com.huotu.android.couponsleague.base.BaseFragment
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.utils.newIntent
import com.huotu.android.couponsleague.widget.FrescoImageLoader
import com.youth.banner.Banner
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_index.*
import kotlinx.android.synthetic.main.fragment_recommand.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecommandFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RecommandFragment : BaseFragment<IPresenter>() , SwipeRefreshLayout.OnRefreshListener , OnBannerListener {

    private var category: String? = null
    private var dataAdapter: DataAdapter?=null
    private var data= ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(ARG_CATEGORY)

        }
    }


    override fun initView() {
        data.add("sss")
        data.add("dddd")

        dataAdapter = DataAdapter(data)

        recommand_recyclerView.layoutManager= GridLayoutManager(context,2)

        var header = LayoutInflater.from(context).inflate(R.layout.layout_recommand_header, null )


        var bannerUrl = ArrayList<String>()
        bannerUrl.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg")
        bannerUrl.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3566.jpg")
        bannerUrl.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3757.jpg")
        var indexBanner = header.findViewById<Banner>(R.id.recommand_banner)
        indexBanner.setImageLoader(FrescoImageLoader())
        indexBanner.setImages(bannerUrl)
        indexBanner.start()

        dataAdapter!!.addHeaderView(header)

        var banner = header.findViewById<Banner>(R.id.recommand_banner)
        banner.setOnBannerListener(this)

        recommand_recyclerView.adapter = dataAdapter

        index_refreshLayout.setOnRefreshListener(this)

        var image1 = header.findViewById<SimpleDraweeView>(R.id.recommand_image_1)
        var image2 = header.findViewById<SimpleDraweeView>(R.id.recommand_image_2)
        var image3 = header.findViewById<SimpleDraweeView>(R.id.recommand_image_3)
        var image4 = header.findViewById<SimpleDraweeView>(R.id.recommand_image_4)
        image1.setImageURI("http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg")
        image2.setImageURI("http://app.infunpw.com/commons/images/cinema/cinema_films/3566.jpg")
        image3.setImageURI("http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg")
        image4.setImageURI("http://app.infunpw.com/commons/images/cinema/cinema_films/3757.jpg")

        var countDown = header.findViewById<CountdownView>(R.id.recommand_countdown)
        countDown.start(15454545)

        var horizontalBanner = header.findViewById<RecyclerView>(R.id.recommand_banner_6)
        horizontalBanner.layoutManager= LinearLayoutManager(context , LinearLayout.HORIZONTAL ,false )

        var horizontalBannerUrl = ArrayList<String>()
        horizontalBannerUrl.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg")
        horizontalBannerUrl.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3566.jpg")
        horizontalBannerUrl.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3757.jpg")
        var horizontalBannerAdapter = HorizontalBannerAdapter(horizontalBannerUrl)
        horizontalBanner.adapter=horizontalBannerAdapter
    }

    override fun fetchData() {

    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_recommand
    }

    override fun onRefresh() {

    }

    override fun OnBannerClick(position: Int) {
        newIntent<DetailActivity>()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecommandFragment.
         */
        @JvmStatic
        fun newInstance(category: String) =
                RecommandFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_CATEGORY, category)
                    }
                }
    }
}
