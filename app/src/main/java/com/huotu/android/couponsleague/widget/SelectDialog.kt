package com.huotu.android.couponsleague.widget

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Size
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.huotu.android.couponsleague.R
import com.huotu.android.couponsleague.adapter.SelectAdapter
import com.huotu.android.couponsleague.bean.KeyValue

class SelectDialog(context: Context):BaseQuickAdapter.OnItemClickListener {

    internal var dialog: AlertDialog
    var selectListener :SelectListener?=null

    var recyclerView:RecyclerView?=null
    var selectAdapter:SelectAdapter?=null
    var data=ArrayList<KeyValue>()

    interface SelectListener{
        fun onSelected(keyValue: KeyValue)
    }

    init {
        val builder = AlertDialog.Builder(context)
        dialog = builder.create()
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_select_dialog, null)
        dialog.setView(view, 0, 0, 0, 0)

        recyclerView = view.findViewById(R.id.select_dialog_recyclerView)
        selectAdapter= SelectAdapter(data)
        recyclerView!!.adapter=selectAdapter
        selectAdapter!!.onItemClickListener=this
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        if(selectListener!=null){
            selectListener!!.onSelected( data[position] )
        }
    }

    fun dismiss() {
        dialog.dismiss()
    }

    fun show( view : View ,  data :ArrayList<KeyValue> ,  selectListener: SelectListener){

        this.data=data
        selectAdapter!!.setNewData(this.data)

        this.selectListener = selectListener

        dialog.setOnKeyListener { dialog, keyCode, event ->
            if(keyCode==KeyEvent.KEYCODE_BACK){
                dialog.dismiss()
            }
            true
        }


        var size=IntArray(2)
        view.getLocationInWindow(size)
        dialog.window.attributes.x = size[0]
        dialog.window.attributes.y =size[1]
        dialog.window.attributes.gravity= Gravity.TOP
        dialog.show()
    }

}