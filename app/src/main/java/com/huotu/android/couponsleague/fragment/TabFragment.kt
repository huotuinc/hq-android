package com.huotu.android.couponsleague.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.adapter.CategoryAdapter
import com.huotu.android.couponsleague.adapter.DataAdapter
import com.huotu.android.couponsleague.base.BaseFragment
import com.huotu.android.couponsleague.bean.Category
import com.huotu.android.couponsleague.mvp.IPresenter
import kotlinx.android.synthetic.main.fragment_tab.*
import kotlinx.android.synthetic.main.layout_column.*


const val ARG_CATEGORY = "category"


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TabFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TabFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TabFragment : BaseFragment<IPresenter>() ,View.OnClickListener{

    private var category: String? = null

    private var categoryList=ArrayList<Category>()
    private var categoryAdapter:CategoryAdapter?=null
    private var dataList =ArrayList<String>()
    private var dataAdapter:DataAdapter?=null
    private var column_price_sort= 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(ARG_CATEGORY)
        }
    }
    

    fun onButtonPressed(uri: Uri) {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    override fun initView() {
        column_lay_price.setOnClickListener(this)

        categoryAdapter = CategoryAdapter(categoryList)
        tab_recyclerview_class.layoutManager = GridLayoutManager(context , 4)
        tab_recyclerview_class.adapter = categoryAdapter
        dataAdapter=DataAdapter(dataList)
        tab_recyclerview_list.layoutManager=GridLayoutManager(context,2)
        tab_recyclerview_list.adapter=dataAdapter
    }

    override fun fetchData() {
        categoryList.clear()
        categoryList.add(Category(1 ,"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg" ,"T桖"))
        categoryList.add(Category(2 ,"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg" ,"休闲裤"))
        categoryList.add(Category(3, "http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg" ,"外套"))
        categoryList.add(Category(4,"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg" ,"短裤"))
        categoryList.add(Category(5,"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg" ,"牛仔裤"))
        categoryList.add(Category(6,"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg" ,"寸衫"))
        categoryList.add(Category(7,"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg" ,"套装"))
        categoryList.add(Category(8,"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg" ,"牛仔衣"))
        //categoryList.add(Category(9,"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg" ,"抖音款"))
        categoryAdapter!!.setNewData(categoryList)

        for(i in 0..20) {
            dataList.add(i.toString())
        }
        dataAdapter!!.notifyDataSetChanged()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_tab
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.column_lay_price->{
                if(column_price_sort==0) {
                    column_price_icon.setImageResource(R.mipmap.up1)
                    column_price_sort=1
                }else{
                    column_price_icon.setImageResource(R.mipmap.down1)
                    column_price_sort=0
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment TabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance( category: String) =
                TabFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_CATEGORY, category)
                    }
                }
    }
}
