package com.huotu.android.couponsleague.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object KeybordUtils{

    fun openKeybord(context: Context , editText: EditText){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText , InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED , InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun closeKeyboard(context: Context, editText: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    fun closeKeyboard(mActivity: Activity) {
        val view = mActivity.window.peekDecorView()
        if (view != null) {
            val inputmanger = mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputmanger.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}