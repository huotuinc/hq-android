package com.huotu.android.couponsleague.activity


import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
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

class MainActivity : BaseActivity<MainContract.Presenter>() ,View.OnClickListener ,ViewPager.OnPageChangeListener{

    var fragments = ArrayList<BaseFragment<IPresenter>>()
    var fragmentAdapter : MainFragmentAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_index.setOnClickListener(this)
        bottom_benefit.setOnClickListener(this)
        bottom_quan.setOnClickListener(this)
        bottom_my.setOnClickListener(this)
        main_viewPager.addOnPageChangeListener(this)

        initFragments()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.bottom_index->{
                main_viewPager.setCurrentItem(0,true)
                changeMenuIcon(0)
            }
            R.id.bottom_benefit->{
                main_viewPager.setCurrentItem(1,true)
                changeMenuIcon(1)
            }
            R.id.bottom_quan->{
                main_viewPager.setCurrentItem(2,true)
                changeMenuIcon(2)
            }
            R.id.bottom_my->{
                main_viewPager.setCurrentItem(3,true)
                changeMenuIcon(3)
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

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        changeMenuIcon(position)
    }

    private fun changeMenuIcon(index:Int) {

        bottom_index_image.setImageResource(if (index == 0) R.mipmap.home2 else R.mipmap.home)
        bottom_index_title.setTextColor(if(index==0) ContextCompat.getColor (this , R.color.textcolor) else ContextCompat.getColor(this , R.color.textcolor2 ))
        bottom_benefit_image.setImageResource(if (index == 1) R.mipmap.benefit2 else R.mipmap.benefit)
        bottom_benefit_title.setTextColor( if (index ==1) ContextCompat.getColor(this , R.color.textcolor) else ContextCompat.getColor(this, R.color.textcolor2) )
        bottom_quan_image.setImageResource(if(index==2) R.mipmap.quan2 else R.mipmap.quan)
        bottom_quan_title.setTextColor( if (index ==2) ContextCompat.getColor(this , R.color.textcolor) else ContextCompat.getColor(this, R.color.textcolor2) )
        bottom_my_image.setImageResource(if(index==3)R.mipmap.my2 else  R.mipmap.my)
        bottom_my_title.setTextColor( if (index ==3) ContextCompat.getColor(this , R.color.textcolor) else ContextCompat.getColor(this, R.color.textcolor2) )
    }
}
