package com.huotu.android.couponsleague.widget

import android.app.AlertDialog
import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.huotu.android.couponsleague.R

/**
 * @author  jinxiangdong
 * @date 2016/4/29.
 */
class TipAlertDialog(context: Context, openByBrowser: Boolean) : View.OnClickListener {
    internal var dialog: AlertDialog
    internal var context: Context? = null
    internal var titleText: TextView
    internal var messageText: TextView
    internal var btn_left: Button
    internal var btn_right: Button
    internal var openByBrowser = true

    init {
        val builder = AlertDialog.Builder(context)
        dialog = builder.create()
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_tipalert_dialog, null)
        dialog.setView(view, 0, 0, 0, 0)
        titleText = view.findViewById(R.id.titletext)
        messageText = view.findViewById(R.id.messagetext)
        btn_left = view.findViewById(R.id.btn_left)
        btn_right = view.findViewById(R.id.btn_right)
        btn_left.setOnClickListener(this)
        btn_right.setOnClickListener(this)
        this.openByBrowser = openByBrowser
    }

    fun dismiss() {
        dialog.dismiss()
    }

    fun show(title: String, message: String, cancelListener: View.OnClickListener, confirmListener: View.OnClickListener) {
        show(title, message, "", R.color.black, true, true, cancelListener, confirmListener)
    }

    @JvmOverloads
    fun show(title: String, message: String, url: String, titleColor: Int = R.color.black,
             isShowLeft: Boolean = true, isShowRight: Boolean = true, cancelListener: View.OnClickListener? = null, confirmListener: View.OnClickListener? = null) {
        titleText.text = title
        titleText.setTextColor(titleColor)
        messageText.text = message
        btn_right.tag = url
        btn_left.visibility = if (isShowLeft) View.VISIBLE else View.GONE
        btn_right.visibility = if (isShowRight) View.VISIBLE else View.GONE

        if (cancelListener != null) {
            btn_left.setOnClickListener(cancelListener)
        }
        if (confirmListener != null) {
            btn_right.setOnClickListener(confirmListener)
        }

        dialog.setOnKeyListener { dialog, keyCode, event ->
            //return false;
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                btn_left.performClick()
            }
            true
        }


        dialog.show()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_left) {
            dialog.dismiss()
        } else if (v.id == R.id.btn_right) {
            dialog.dismiss()

            if (null == btn_right.tag) return
            //val linkUrl = btn_right.tag.toString()
            //if(TextUtils.isEmpty(linkUrl)) return;

        }
    }
}
