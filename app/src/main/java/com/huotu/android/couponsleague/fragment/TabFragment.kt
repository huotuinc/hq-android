package com.huotu.android.couponsleague.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.adapter.CategoryAdapter
import com.huotu.android.couponsleague.adapter.DataAdapter
import com.huotu.android.couponsleague.base.BaseFragment
import com.huotu.android.couponsleague.bean.Category
import com.huotu.android.couponsleague.mvp.IPresenter
import kotlinx.android.synthetic.main.fragment_tab.*


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
class TabFragment : BaseFragment<IPresenter>() {

    private var category: String? = null

    private var categoryList=ArrayList<Category>()
    private var categoryAdapter:CategoryAdapter?=null
    private var dataList =ArrayList<String>()
    private var dataAdapter:DataAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getString(ARG_CATEGORY)
        }
    }
    

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    override fun initView() {

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
        categoryList.add(Category(9,"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg" ,"抖音款"))

        categoryAdapter = CategoryAdapter(categoryList)

        tab_recyclerview_class.layoutManager = GridLayoutManager(context , 4)
        tab_recyclerview_class.adapter = categoryAdapter

        for(i in 0..40) {
            dataList.add(i.toString())
        }
        tab_recyclerview_list.layoutManager=GridLayoutManager(context,2)
        dataAdapter=DataAdapter(dataList)
        tab_recyclerview_list.adapter=dataAdapter
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_tab
    }

//    override fun getPageTitle(): String? {
//        return category
//    }

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
