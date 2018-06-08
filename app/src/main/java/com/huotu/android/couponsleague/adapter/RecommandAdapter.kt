package com.huotu.android.couponsleague.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import cn.iwgang.countdownview.CountdownView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.facebook.drawee.view.SimpleDraweeView
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.bean.GoodBean
import com.huotu.android.couponsleague.bean.recommand.*
import com.huotu.android.couponsleague.utils.DensityUtils
import com.huotu.android.couponsleague.widget.FrescoImageLoader
import com.youth.banner.Banner


class RecommandAdapter(data : ArrayList<MultiItemEntity>)
    : BaseMultiItemQuickAdapter<MultiItemEntity , BaseViewHolder>(data) {

    init {
        addItemType( ItemTypeEnum.BANNER.type , R.layout.layout_recommand_item_1 )
        addItemType(ItemTypeEnum.ONE_COLLOMN_SIMPLE.type , R.layout.layout_recommand_item_2)
        addItemType(ItemTypeEnum.ONE_ROW_COUNTDOWN.type , R.layout.layout_recommand_item_3)
        addItemType(ItemTypeEnum.ONE_ROW_GOODS.type , R.layout.layout_recommand_item_4)
        addItemType(ItemTypeEnum.ONE_ROW_TITLE.type , R.layout.layout_recommand_item_5)
        addItemType(ItemTypeEnum.ONE_ROW_CAN_SCROLL_BANNER.type,R.layout.layout_recommand_item_6)
        addItemType(ItemTypeEnum.ONE_COLLOMN_GOODS.type,R.layout.layout_recommand_item_7)
    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        when(helper!!.itemViewType){
            ItemTypeEnum.BANNER.type->{
                set_1(helper , item)
            }
            ItemTypeEnum.ONE_COLLOMN_SIMPLE.type->{
                set_2(helper,item)
            }
            ItemTypeEnum.ONE_ROW_COUNTDOWN.type->{
                set_3(helper,item)
            }
            ItemTypeEnum.ONE_ROW_GOODS.type->{
                set_4(helper,item)
            }
            ItemTypeEnum.ONE_ROW_TITLE.type->{
                set_5(helper,item)
            }
            ItemTypeEnum.ONE_ROW_CAN_SCROLL_BANNER.type->{
                set_6(helper,item)
            }
            ItemTypeEnum.ONE_COLLOMN_GOODS.type->{
                set_7(helper,item)
            }
        }
    }

    private fun set_1(helper: BaseViewHolder?,item: MultiItemEntity? ){
        var urls = (item as RecommandItem1).data
        var banner = helper!!.getView<Banner>(R.id.recommand_banner)
        banner.setImageLoader(FrescoImageLoader(banner , DensityUtils.getScreenWidth(mContext) ))
        banner.setImages(urls)
        banner.start()
    }

    private fun set_2(helper: BaseViewHolder?,item: MultiItemEntity?){
        var image = helper!!.getView<SimpleDraweeView>(R.id.recommand_image_1)
        image.setImageURI("http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg")
    }

    private fun set_3(helper: BaseViewHolder?,item: MultiItemEntity?){
        var countDown = helper!!.getView<CountdownView>(R.id.recommand_countdown)
        countDown.start(15454545)
    }

    private fun set_4(helper: BaseViewHolder?,item: MultiItemEntity?){
        var bean = item as RecommandItem4
        helper!!.getView<SimpleDraweeView>(R.id.good_item_image)
                .setImageURI(bean.data.imgSrc)
    }

    private fun set_5(helper: BaseViewHolder?,item: MultiItemEntity?){

        var bean = item as RecommandItem5
        helper!!.setText(R.id.recommand_banner_5 , bean.title )
    }
    private fun set_6(helper: BaseViewHolder?,item: MultiItemEntity?){
        var horizontalBanner = helper!!.getView<RecyclerView>(R.id.recommand_banner_6)
        horizontalBanner.layoutManager= LinearLayoutManager( mContext , LinearLayout.HORIZONTAL ,false )

        var horizontalBannerUrl = ArrayList<String>()
        horizontalBannerUrl.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg")
        horizontalBannerUrl.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3566.jpg")
        horizontalBannerUrl.add("http://app.infunpw.com/commons/images/cinema/cinema_films/3757.jpg")
        var horizontalBannerAdapter = HorizontalBannerAdapter( horizontalBannerUrl)
        horizontalBanner.adapter=horizontalBannerAdapter
    }

    private fun set_7(helper: BaseViewHolder?,item: MultiItemEntity?){
        var bean = item as RecommandItem7
        helper!!.getView<SimpleDraweeView>(R.id.good_item_1_logo).setImageURI(bean.data.imgSrc)
    }
}