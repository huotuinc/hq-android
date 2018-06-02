package com.huotu.android.couponsleague.activity


import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.base.BaseFragment
import com.huotu.android.couponsleague.fragment.*
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.mvp.contract.MainContract
import com.huotu.android.couponsleague.mvp.presenter.MainPresenter
import com.huotu.android.couponsleague.utils.showToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom_menu.*

class MainActivity : BaseActivity<MainContract.Presenter>() ,View.OnClickListener {

    var fragments = ArrayList<BaseFragment<IPresenter>>()
    var fragmentAdapter : MainFragmentAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_index.setOnClickListener(this)
        bottom_benefit.setOnClickListener(this)
        bottom_quan.setOnClickListener(this)
        bottom_my.setOnClickListener(this)

        initFragments()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.bottom_index->{
                main_viewPager.setCurrentItem(0,true)
                showToast("index")
            }
            R.id.bottom_benefit->{
                main_viewPager.setCurrentItem(1,true)
                showToast("benefit")
            }
            R.id.bottom_quan->{
                main_viewPager.setCurrentItem(2,true)
                showToast("quan")
            }
            R.id.bottom_my->{
                main_viewPager.setCurrentItem(3,true)
                showToast("my")
            }
        }
    }

    private fun initFragments(){

        fragments.clear()
        fragments.add(IndexFragment.newInstance())
        fragments.add(BenefitsFragment.newInstance())
        fragments.add(QuanFragment.newInstance())
        fragments.add(MyFragment.newInstance())

        fragmentAdapter = MainFragmentAdapter(supportFragmentManager , fragments )
        main_viewPager.adapter = fragmentAdapter
        //main_viewPager.addOnPageChangeListener(this)
        //main_tab.setupWithViewPager(main_viewPager,true)
        //main_tab.addOnTabSelectedListener(this)

    }

}
