package com.huotu.android.couponsleague.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import com.huotu.android.couponsleague.R

/**
 * Created by jinxiangdong on 2016/11/17.
 */
class RecycleItemDivider
/**
 * 构造方法传入布局方向，不可不传
 * @param context
 * @param orientation
 */
@JvmOverloads constructor(context: Context, orientation: Int, itemSize: Int = 1, @ColorRes color: Int = R.color.line_color)
    : RecyclerView.ItemDecoration() {
    /*
     * RecyclerView的布局方向，默认先赋值
     * 为纵向布局
     * RecyclerView 布局可横向，也可纵向
     * 横向和纵向对应的分割想画法不一样
     * */
    private var mOrientation = LinearLayoutManager.VERTICAL

    /**
     * item之间分割线的size，默认为1
     */
    private var mItemSize = 1

    /**
     * 绘制item分割线的画笔，和设置其属性
     * 来绘制个性分割线
     */
    private val mPaint: Paint
    /***
     * 风格线颜色
     */
    @ColorRes
    private var mDividerColor = R.color.line_color

    init {
        this.mOrientation = orientation
        this.mItemSize = itemSize
        this.mDividerColor = color

        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw IllegalArgumentException("请传入正确的参数")
        }
        mItemSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mItemSize.toFloat(), context.resources.displayMetrics).toInt()
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.color = ContextCompat.getColor(context, mDividerColor)
        /*设置填充*/
        mPaint.style = Paint.Style.FILL
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    /**
     * 绘制纵向 item 分割线
     * @param canvas
     * @param parent
     */
    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.measuredWidth - parent.paddingRight
        val childSize = parent.childCount
        for (i in 0 until childSize) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + layoutParams.bottomMargin
            val bottom = top + mItemSize
            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
        }
    }

    /**
     * 绘制横向 item 分割线
     * @param canvas
     * @param parent
     */
    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.measuredHeight - parent.paddingBottom
        val childSize = parent.childCount
        for (i in 0 until childSize) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + layoutParams.rightMargin
            val right = left + mItemSize
            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
        }
    }

    /**
     * 设置item分割线的size
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, mItemSize)
        } else {
            outRect.set(0, 0, mItemSize, 0)
        }
    }

}
