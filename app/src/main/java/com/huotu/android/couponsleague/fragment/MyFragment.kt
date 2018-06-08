package com.huotu.android.couponsleague.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.activity.*
import com.huotu.android.couponsleague.base.BaseFragment
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.utils.newIntent
import kotlinx.android.synthetic.main.fragment_my.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MyFragment : BaseFragment<IPresenter>() , View.OnClickListener {

    private var param1: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun initView() {
        my_setting.setOnClickListener(this)
        my_lay_order.setOnClickListener(this)
        my_lay_favorite.setOnClickListener(this)
        my_lay_message.setOnClickListener(this)
        my_lay_invite.setOnClickListener(this)
        my_lay_zhuan.setOnClickListener(this)
    }

    override fun fetchData() {

    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_my
    }


    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.my_setting->{
                newIntent<SetActivity>()
            }
            R.id.my_lay_order->{
                newIntent<OrderActivity>()
            }
            R.id.my_lay_favorite->{
                newIntent<FavoriteActivity>()
            }
            R.id.my_lay_message->{
                newIntent<MessageActivity>()
            }
            R.id.my_lay_invite->{
                newIntent<InviteActivity>()
            }
            R.id.my_lay_zhuan->{
                newIntent<MyMoneyActivity>()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                MyFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
