package com.huotu.android.couponsleague.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.adapter.OrderAdapter
import com.huotu.android.couponsleague.base.BaseFragment
import com.huotu.android.couponsleague.bean.OrderBean
import com.huotu.android.couponsleague.bean.OrderStatusEnum
import com.huotu.android.couponsleague.bean.OrderTypeEnum
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.widget.RecycleItemDivider
import kotlinx.android.synthetic.main.fragment_order.*


private const val ARG_ORDERTYPE = "ordertype"
private const val ARG_ORDERSTATUS= "orderstatus"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class OrderFragment : BaseFragment<IPresenter>() {

    private var orderType: Int = OrderTypeEnum.PINDUODUO.id
    private var orderStatus: Int = OrderStatusEnum.ALL.id
    private var orderAdapter :OrderAdapter?=null
    private var data=ArrayList<OrderBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            orderType = it.getInt(ARG_ORDERTYPE , OrderTypeEnum.PINDUODUO.id)
            orderStatus = it.getInt(ARG_ORDERSTATUS , OrderStatusEnum.ALL.id)
        }
    }


    override fun initView() {
        for(i in 0..10){
            data.add(OrderBean(i,i.toString(),"sadfa","","asdf","23","3"))
        }

        orderAdapter = OrderAdapter(data)

        order_recyclerview.layoutManager=LinearLayoutManager(context)
        order_recyclerview.adapter = orderAdapter
        order_recyclerview.addItemDecoration(RecycleItemDivider(context!!,LinearLayoutManager.VERTICAL,1))
    }

    override fun fetchData() {
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_order
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderFragment.
         */
        @JvmStatic
        fun newInstance( orderType: Int, orderStatus: Int) =
                OrderFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ORDERTYPE, orderType)
                        putInt(ARG_ORDERSTATUS, orderStatus)
                    }
                }
    }
}
