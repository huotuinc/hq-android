package com.huotu.android.couponsleague.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.base.BaseFragment
import com.huotu.android.couponsleague.mvp.IPresenter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BenefitsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class BenefitsFragment : BaseFragment<IPresenter>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun initView() {

    }

    override fun fetchData() {
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_benefits
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BenefitsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                BenefitsFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
