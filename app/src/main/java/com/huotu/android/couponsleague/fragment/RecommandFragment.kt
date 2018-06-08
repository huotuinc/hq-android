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
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.facebook.drawee.view.SimpleDraweeView

import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.activity.DetailActivity
import com.huotu.android.couponsleague.adapter.DataAdapter
import com.huotu.android.couponsleague.adapter.HorizontalBannerAdapter
import com.huotu.android.couponsleague.adapter.RecommandAdapter
import com.huotu.android.couponsleague.adapter.RecommandDevider
import com.huotu.android.couponsleague.base.BaseFragment
import com.huotu.android.couponsleague.bean.GoodBean
import com.huotu.android.couponsleague.bean.recommand.*
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.utils.DensityUtils
import com.huotu.android.couponsleague.utils.newIntent
import com.huotu.android.couponsleague.widget.FrescoImageLoader
import com.youth.banner.Banner
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_recommand.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecommandFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class RecommandFragment : BaseFragment<IPresenter>()
        , SwipeRefreshLayout.OnRefreshListener
        , OnBannerListener {

    private var category: String? = null
    private var dataAdapter: DataAdapter?=null
    private var recommandAdapter:RecommandAdapter?=null
    private var data= ArrayList<String>()
    private var recommands = ArrayList<MultiItemEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(ARG_CATEGORY)

        }
    }

    private fun mockData():ArrayList<MultiItemEntity>{
        recommands.clear()

        var bannerUrl = ArrayList<String>()
        bannerUrl.add("http://image.tkcm888.com/adSet_2018-06-01_f406f8550f0f4b21b41fca881bbcb11415278577614883710.png")
        bannerUrl.add("http://image.tkcm888.com/adSet_2018-05-31_56440f86ea1d4d60a9a4d725e26e62c015277545962763144.png")
        bannerUrl.add("http://image.tkcm888.com/adSet_2018-05-31_a13475823f524d5f8b3b9480673e339915277602221601122.png")
        bannerUrl.add("http://image.tkcm888.com/adSet_2018-06-04_d18eb67c0fbc43a398fc7c55f818122415281204839937212.png")
        var item1 = RecommandItem1(bannerUrl)
        recommands.add(item1)



        var bean = GoodBean(1,"","","", "","http://t00img.yangkeduo.com/t01img/images/2018-06-04/c421980286368efa5c730b0e6404f7ec.jpeg",null,"","","","","","")
        var item2 = RecommandItem2(bean)
        recommands.add(item2)
        bean = GoodBean(1,"","","","","http://t00img.yangkeduo.com/t01img/images/2018-06-04/c421980286368efa5c730b0e6404f7ec.jpeg",null,"","","","","","")
        item2 = RecommandItem2(bean)
        recommands.add(item2)
        bean = GoodBean(1,"","","","","http://t00img.yangkeduo.com/t01img/images/2018-06-04/c421980286368efa5c730b0e6404f7ec.jpeg",null,"","","","","","")
        item2 = RecommandItem2(bean)
        recommands.add(item2)
        bean = GoodBean(1,"","","","","http://t00img.yangkeduo.com/t01img/images/2018-06-04/c421980286368efa5c730b0e6404f7ec.jpeg",null,"","","","","","")
        item2 = RecommandItem2(bean)
        recommands.add(item2)

        var item3 = RecommandItem3(1152021)
        recommands.add(item3)

        bean = GoodBean(1,"","","","","http://t00img.yangkeduo.com/t01img/images/2018-06-04/c421980286368efa5c730b0e6404f7ec.jpeg",null,"","","","","","")
        var item4 = RecommandItem4(bean)
        recommands.add(item4)
        bean = GoodBean(1,"","","","","http://t00img.yangkeduo.com/t01img/images/2018-06-04/c421980286368efa5c730b0e6404f7ec.jpeg",null,"","","","","","")
        item4 = RecommandItem4(bean)
        recommands.add(item4)
        bean = GoodBean(1,"","","","","http://t00img.yangkeduo.com/t01img/images/2018-06-04/c421980286368efa5c730b0e6404f7ec.jpeg",null,"","","","","","")
        item4 = RecommandItem4(bean)
        recommands.add(item4)

        var item5 = RecommandItem5("厂家直供")
        recommands.add(item5)

        var goods = ArrayList<GoodBean>()
        goods.add(bean)
        goods.add(bean)
        goods.add(bean)
        goods.add(bean)
        var item6 = RecommandItem6(goods)
        recommands.add(item6)

        var item7 = RecommandItem5("新品首发")
        recommands.add(item7)


        bean = GoodBean(1,"","","","","http://t00img.yangkeduo.com/t01img/images/2018-06-04/c421980286368efa5c730b0e6404f7ec.jpeg",null,"","","","","","")
        var item8 = RecommandItem7(bean)
        recommands.add(item8)
        bean = GoodBean(1,"","","","","http://t00img.yangkeduo.com/t01img/images/2018-06-04/c421980286368efa5c730b0e6404f7ec.jpeg",null,"","","","","","")
        item8 = RecommandItem7(bean)
        recommands.add(item8)
        bean = GoodBean(1,"","","","","http://t00img.yangkeduo.com/t01img/images/2018-06-04/c421980286368efa5c730b0e6404f7ec.jpeg",null,"","","","","","")
        item8 = RecommandItem7(bean)
        recommands.add(item8)
        bean = GoodBean(1,"","","","","http://t00img.yangkeduo.com/t01img/images/2018-06-04/c421980286368efa5c730b0e6404f7ec.jpeg",null,"","","","","","")
        item8 = RecommandItem7(bean)
        recommands.add(item8)
        bean = GoodBean(1,"","","","","http://t00img.yangkeduo.com/t01img/images/2018-06-04/c421980286368efa5c730b0e6404f7ec.jpeg",null,"","","","","","")
        item8 = RecommandItem7(bean)
        recommands.add(item8)

        return recommands
    }


    override fun initView() {
        data.add("sss")
        data.add("dddd")
        dataAdapter = DataAdapter(data)

        mockData()

        recommandAdapter= RecommandAdapter(recommands)

        recommand_recyclerView.layoutManager= GridLayoutManager(context,2)
        recommandAdapter!!.setSpanSizeLookup(object:BaseQuickAdapter.SpanSizeLookup{
            override fun getSpanSize(gridLayoutManager: GridLayoutManager?, position: Int): Int {
                if( recommandAdapter!!.getItemViewType(position) == ItemTypeEnum.BANNER.type ){
                    return 2
                }else if(recommandAdapter!!.getItemViewType(position) == ItemTypeEnum.ONE_COLLOMN_SIMPLE.type){
                    return 1
                }else if(recommandAdapter!!.getItemViewType(position)==ItemTypeEnum.ONE_ROW_COUNTDOWN.type){
                    return 2
                }else if(recommandAdapter!!.getItemViewType(position)==ItemTypeEnum.ONE_ROW_TITLE.type){
                    return 2
                }else if(recommandAdapter!!.getItemViewType(position)==ItemTypeEnum.ONE_ROW_GOODS.type){
                    return 2
                }else if(recommandAdapter!!.getItemViewType(position)==ItemTypeEnum.ONE_ROW_CAN_SCROLL_BANNER.type){
                    return 2
                }else if(recommandAdapter!!.getItemViewType(position)==ItemTypeEnum.ONE_COLLOMN_GOODS.type){
                    return 1
                }else{
                    return  1
                }
            }
        })

        var header = LayoutInflater.from(context).inflate(R.layout.layout_recommand_header, null )


        var bannerUrl = ArrayList<String>()
        bannerUrl.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg")
        bannerUrl.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3566.jpg")
        bannerUrl.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3757.jpg")
        var indexBanner = header.findViewById<Banner>(R.id.recommand_banner)
        indexBanner.setImageLoader(FrescoImageLoader( indexBanner , DensityUtils.getScreenWidth(context!!)))
        indexBanner.setImages(bannerUrl)
        indexBanner.start()

        dataAdapter!!.addHeaderView(header)

        var banner = header.findViewById<Banner>(R.id.recommand_banner)
        banner.setOnBannerListener(this)

        recommand_recyclerView.adapter = recommandAdapter //dataAdapter
        recommand_recyclerView.addItemDecoration(RecommandDevider(recommandAdapter!!,context!!))

        recommand_refreshLayout.setOnRefreshListener(this)

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
