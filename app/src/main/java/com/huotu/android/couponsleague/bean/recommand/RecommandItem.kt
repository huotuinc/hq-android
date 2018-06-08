package com.huotu.android.couponsleague.bean.recommand

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.huotu.android.couponsleague.bean.GoodBean

data class RecommandItem1(var data :ArrayList<String>)
    :MultiItemEntity {

    override fun getItemType(): Int {
        return ItemTypeEnum.BANNER.type
    }
}

data class RecommandItem2(var data :GoodBean) :MultiItemEntity{
    override fun getItemType(): Int {
        return ItemTypeEnum.ONE_COLLOMN_SIMPLE.type
    }
}

data class RecommandItem3(var data: Long):MultiItemEntity{

    override fun getItemType(): Int {
        return ItemTypeEnum.ONE_ROW_COUNTDOWN.type
    }
}

data class RecommandItem4(var data : GoodBean):MultiItemEntity{

    override fun getItemType(): Int {
        return ItemTypeEnum.ONE_ROW_GOODS.type
    }
}

data class RecommandItem5(var title:String):MultiItemEntity{
    override fun getItemType(): Int {
        return ItemTypeEnum.ONE_ROW_TITLE.type
    }
}

data class RecommandItem6(var data : ArrayList<GoodBean>):MultiItemEntity{
    override fun getItemType(): Int {
        return ItemTypeEnum.ONE_ROW_CAN_SCROLL_BANNER.type
    }
}

data class RecommandItem7(var data : GoodBean ):MultiItemEntity{
    override fun getItemType(): Int {
        return ItemTypeEnum.ONE_COLLOMN_GOODS.type
    }
}