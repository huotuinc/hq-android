package com.huotu.android.couponsleague.bean

/**
 * Created by jinxiangdong on 2018/2/5.
 */

data class InitDataBean (

    var userInfo: UserBean? = null,
    /**
     * 关于我们
     */
    var about_url: String? = null,
    /**
     * 注册协议
     */
    var register_agreement_url : String? = null,
    /**
     * 征信查询授权
     */
    var creditAuthUrl: String? = null,
    /**
     * 贷款进度查询地址
     */
    var loanProjectProcessUrl: String? = null,
    /**
     * 有信用户信息
     */
    var yxUserInfo: UserBean? = null,
    /**
     * 人脸和身份证的比对范围值
     */
    var faceErrorValue: String? = null,
    /**
     * 我的借款地址
     */
    var applyListUrl: String? = null,
    /**
     * 信用报表地址
     */
    var creditUrl: String? = null,
    /**
     * 发布记录地址
     */
    var publishListUrl: String? = null,
    /**
     * 联系我们
     */
    var contact_us_url:String="",
    /**
     *物流页面地址
     */
    var logistics_url:String="",
    /**
     * 手机回收合同
     */
    var mobile_agreement_url:String="",
    /**
     * 手机详情页面地址
     */
    var mobile_info_url:String="",
    /**
     * 回收页面地址
     */
    var recycle_list_url:String="",
    /**
     * 进度页面地址
     */
    var recycle_progress_url:String="",
    /**
     * 结果页面地址
     */
    var result_url:String=""
)
