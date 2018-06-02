package com.huotu.android.couponsleague.activity


import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.base.BaseActivity
import com.huotu.android.couponsleague.bean.Constants
import com.huotu.android.couponsleague.mvp.IPresenter
import com.huotu.android.couponsleague.utils.KeybordUtils
import com.huotu.android.couponsleague.utils.SPUtils
import com.huotu.android.couponsleague.utils.showToast
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.layout_search_header.*
import me.gujun.android.taggroup.TagGroup
import java.util.*
import kotlin.collections.ArrayList


class SearchActivity : BaseActivity<IPresenter>() , TextView.OnEditorActionListener, View.OnClickListener , TagGroup.OnTagClickListener {

    var tags : ArrayList<String>?=null
    var hotTags =ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initView()
    }

    private fun initView(){

        hotTags.add("s手动发送")
        hotTags.add("任务")
        hotTags.add("df")
        hotTags.add("打发")
        hotTags.add("reer玩儿玩儿")
        hotTags.add("沃尔沃斯蒂芬")
        search_tags_hot.setTags(hotTags)

        search_tags.setOnTagClickListener(this)
        search_tags_hot.setOnTagClickListener(this)
        search_delete.setOnClickListener(this)
        search_cancel.setOnClickListener(this)

        search_key.setOnEditorActionListener(this)
        KeybordUtils.openKeybord( this ,  search_key)

        tags = ArrayList( SPUtils.getInstance(this,Constants.PREF_SEARCH_FILENAME).readStringSet(Constants.PREF_KEY , Collections.emptySet() ))
        search_tags.setTags(tags)
    }

    override fun onTagClick(tag: String?) {
        showToast(tag!!)
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if( actionId == EditorInfo.IME_ACTION_SEARCH){
            goSearch()
        }
        return false
    }

    fun goSearch(){
        var key = search_key.text.toString()
        if(TextUtils.isEmpty(key))return

        if(!tags!!.contains( key )) {
            tags!!.add(key)
            SPUtils.getInstance(this, Constants.PREF_SEARCH_FILENAME).writeStringSet(Constants.PREF_KEY, tags!!.toSet())
            search_tags.setTags(tags)
        }

        //todo
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.search_delete->{
                delete()
            }
            R.id.search_cancel->{
                finish()
            }
        }

    }

    fun delete(){
        tags!!.clear()
        SPUtils.getInstance(this,Constants.PREF_SEARCH_FILENAME).clear()
        search_key.setText("")
        search_tags.setTags(tags)
    }

    override fun onDestroy() {
        super.onDestroy()
        KeybordUtils.closeKeyboard(this)
    }
}
