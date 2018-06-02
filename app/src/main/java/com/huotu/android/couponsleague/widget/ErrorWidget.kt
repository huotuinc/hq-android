package com.huotu.android.couponsleague.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.huotu.android.couponsleague.R
import kotlinx.android.synthetic.main.layout_error.view.*

/**
 * 错误页面
 */
class ErrorWidget : LinearLayout {

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    fun setError(message: String) {
        errorText.text = message
    }


    private fun init(attrs: AttributeSet?, defStyle: Int) {

        val layoutInflater = LayoutInflater.from(this.context)
        layoutInflater.inflate(R.layout.layout_error, this, true)

    }


}
