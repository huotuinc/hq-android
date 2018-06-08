/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright 2014 Manabu Shimobe
 * Copyright 2016 Chen Sir
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.huotu.android.couponsleague.widget

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.DrawableRes
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseBooleanArray
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.LinearLayout
import android.widget.TextView
import com.huotu.android.couponsleague.R


class ExpandableTextView : LinearLayout, View.OnClickListener {

    protected var mTv: TextView? = null

    protected var mStateTv: TextView?=null // TextView to expand/collapse

    private var mRelayout: Boolean = false

    private var mCollapsed = true // Show short version as default.

    private var mCollapsedHeight: Int = 0

    private var mTextHeightWithMaxLines: Int = 0

    private var mMaxCollapsedLines: Int = 0

    private var mMarginBetweenTxtAndBottom: Int = 0

    private var mExpandDrawable: Drawable? = null

    private var mCollapseDrawable: Drawable? = null

    private var mStateTvGravity: Int = 0

    private var mCollapsedString: String? = null

    private var mExpandString: String? = null

    private var mAnimationDuration: Int = 0

    private var mContentTextSize: Float = 0.toFloat()

    private var mContentTextColor: Int = 0

    private var mContentLineSpacingMultiplier: Float = 0.toFloat()

    private var mStateTextColor: Int = 0

    private var mAnimating: Boolean = false

    private var mShowExpandCollapseDrawable:Boolean=true

    /* Listener for callback */
    private var mListener: OnExpandStateChangeListener? = null

    /* For saving collapsed status when used in ListView */
    private var mCollapsedStatus: SparseBooleanArray? = null
    private var mPosition: Int = 0

    private val mRunnable = Runnable { mMarginBetweenTxtAndBottom = height - mTv!!.height }

    var text: CharSequence?
        get() = if (mTv == null) {
            ""
        } else mTv!!.text
        set(text) {
            mRelayout = true
            mTv!!.text = text
            visibility = if (TextUtils.isEmpty(text)) View.GONE else View.VISIBLE
        }

    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        init(context, attrs)
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    override fun setOrientation(orientation: Int) {
        if (LinearLayout.HORIZONTAL == orientation) {
            throw IllegalArgumentException("ExpandableTextView only supports Vertical Orientation.")
        }
        super.setOrientation(orientation)
    }

    override fun onClick(view: View) {

        if (mStateTv!!.visibility != View.VISIBLE) {
            return
        }

        mCollapsed = !mCollapsed
        mStateTv!!.text = if (mCollapsed) mExpandString else mCollapsedString

        if(mShowExpandCollapseDrawable) {
            mStateTv!!.setCompoundDrawablesWithIntrinsicBounds(if (mCollapsed) mExpandDrawable else mCollapseDrawable, null, null, null)
        }

        if (mCollapsedStatus != null) {
            mCollapsedStatus!!.put(mPosition, mCollapsed)
        }

        // mark that the animation is in progress
        mAnimating = true

        val animation: Animation
        if (mCollapsed) {
            animation = ExpandCollapseAnimation(this, height, mCollapsedHeight)
        } else {
            animation = ExpandCollapseAnimation(this, height, height + mTextHeightWithMaxLines - mTv!!.height)
        }

        animation.setFillAfter(true)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                // clear animation here to avoid repeated applyTransformation() calls
                clearAnimation()
                // clear the animation flag
                mAnimating = false

                // notify the listener
                if (mListener != null) {
                    mListener!!.onExpandStateChanged(mTv, !mCollapsed)
                }
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })

        clearAnimation()
        startAnimation(animation)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        // while an animation is in progress, intercept all the touch events to children to
        // prevent extra clicks during the animation
        return mAnimating
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        findViews()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // If no change, measure and return
        if (!mRelayout || visibility == View.GONE) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }
        mRelayout = false

        // Setup with optimistic case
        // i.e. Everything fits. No button needed
        mStateTv!!.visibility = View.GONE
        mTv!!.maxLines = Integer.MAX_VALUE

        // Measure
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // If the text fits in collapsed mode, we are done.
        if (mTv!!.lineCount <= mMaxCollapsedLines) {
            return
        }

        // Saves the text height w/ max lines
        mTextHeightWithMaxLines = getRealTextViewHeight(mTv!!)

        // Doesn't fit in collapsed mode. Collapse text view as needed. Show
        // button.
        if (mCollapsed) {
            mTv!!.maxLines = mMaxCollapsedLines
        }
        mStateTv!!.visibility = View.VISIBLE

        // Re-measure with new setup
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (mCollapsed) {
            // Gets the margin between the TextView's bottom and the ViewGroup's bottom
            mTv!!.post(mRunnable)
            // Saves the collapsed height of this ViewGroup
            mCollapsedHeight = measuredHeight
        }
    }

    fun setOnExpandStateChangeListener(listener: OnExpandStateChangeListener?) {
        mListener = listener
    }

    fun setText(text: CharSequence?, collapsedStatus: SparseBooleanArray, position: Int) {
        mCollapsedStatus = collapsedStatus
        mPosition = position
        val isCollapsed = collapsedStatus.get(position, true)
        clearAnimation()
        mCollapsed = isCollapsed
        mStateTv!!.text = if (mCollapsed) mExpandString else mCollapsedString
        if(mShowExpandCollapseDrawable) {
            mStateTv!!.setCompoundDrawablesWithIntrinsicBounds(if (mCollapsed) mExpandDrawable else mCollapseDrawable, null, null, null)
        }
        this.text = text
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        requestLayout()
    }

    private fun init(context: Context, attrs: AttributeSet?) {

        LayoutInflater.from(context).inflate(R.layout.expandable_textview, this, true)
        // enforces vertical orientation
        orientation = LinearLayout.VERTICAL

        // default visibility is gone
        visibility = View.GONE

        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ExpandableTextView)
        mMaxCollapsedLines = typedArray.getInt(R.styleable.ExpandableTextView_maxCollapsedLines, MAX_COLLAPSED_LINES)
        mAnimationDuration = typedArray.getInt(R.styleable.ExpandableTextView_animDuration, DEFAULT_ANIM_DURATION)
        mContentTextSize = typedArray.getFloat(R.styleable.ExpandableTextView_contentTextSize, DEFAULT_CONTENT_TEXT_SIZE)
        mContentLineSpacingMultiplier = typedArray.getFloat(R.styleable.ExpandableTextView_contentLineSpacingMultiplier, DEFAULT_CONTENT_TEXT_LINE_SPACING_MULTIPLIER)
        mContentTextColor = typedArray.getColor(R.styleable.ExpandableTextView_contentTextColor, Color.BLACK)

        mExpandDrawable = typedArray.getDrawable(R.styleable.ExpandableTextView_expandDrawable)
        mCollapseDrawable = typedArray.getDrawable(R.styleable.ExpandableTextView_collapseDrawable)
        mStateTvGravity = typedArray.getInt(R.styleable.ExpandableTextView_DrawableAndTextGravity, STATE_TV_GRAVITY_RIGHT)
        mExpandString = typedArray.getString(R.styleable.ExpandableTextView_expandText)
        mCollapsedString = typedArray.getString(R.styleable.ExpandableTextView_collapseText)
        mStateTextColor = typedArray.getColor(R.styleable.ExpandableTextView_expandCollapseTextColor, Color.BLACK)
        mShowExpandCollapseDrawable=typedArray.getBoolean(R.styleable.ExpandableTextView_showExpandCollapseDrawable , true)


        if (mExpandDrawable == null) {
            mExpandDrawable = getDrawable(getContext(), R.mipmap.ic_expand_more_black_12dp);
        }
        if (mCollapseDrawable == null) {
            mCollapseDrawable = getDrawable(getContext(), R.mipmap.ic_expand_less_black_12dp);
        }

        if (mExpandString == null) {
            mExpandString = this.context.getString(R.string.expand_string)
        }
        if (mCollapsedString == null) {
            mCollapsedString = this.context.getString(R.string.collapsed_string)
        }

        typedArray.recycle()

    }

    private fun findViews() {
        mTv = findViewById<View>(R.id.expandable_text) as TextView
        mTv!!.setTextColor(mContentTextColor)
        mTv!!.textSize = mContentTextSize
        mTv!!.setLineSpacing(0f, mContentLineSpacingMultiplier)
        mTv!!.setOnClickListener(this)

        mStateTv = findViewById<View>(R.id.expand_collapse) as TextView
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        if (mStateTvGravity == STATE_TV_GRAVITY_LEFT) {
            params.gravity = Gravity.START
        } else if (mStateTvGravity == STATE_TV_GRAVITY_CENTER) {
            params.gravity = Gravity.CENTER_HORIZONTAL
        } else if (mStateTvGravity == STATE_TV_GRAVITY_RIGHT) {
            params.gravity = Gravity.END
        }
        mStateTv!!.layoutParams = params
        mStateTv!!.text = if (mCollapsed) mExpandString else mCollapsedString
        mStateTv!!.setTextColor(mStateTextColor)
        if(mShowExpandCollapseDrawable) {
            mStateTv!!.setCompoundDrawablesWithIntrinsicBounds(if (mCollapsed) mExpandDrawable else mCollapseDrawable, null, null, null)
            mStateTv!!.compoundDrawablePadding = 10
        }
        mStateTv!!.setOnClickListener(this)
    }

    internal inner class ExpandCollapseAnimation(private val mTargetView: View, private val mStartHeight: Int, private val mEndHeight: Int) : Animation() {

        init {
            duration = mAnimationDuration.toLong()
        }

        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            val newHeight = ((mEndHeight - mStartHeight) * interpolatedTime + mStartHeight).toInt()
            mTv!!.maxHeight = newHeight - mMarginBetweenTxtAndBottom
            mTargetView.layoutParams.height = newHeight
            mTargetView.requestLayout()
        }

        override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
            super.initialize(width, height, parentWidth, parentHeight)
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    interface OnExpandStateChangeListener {
        /**
         * Called when the expand/collapse animation has been finished
         *
         * @param textView   - TextView being expanded/collapsed
         * @param isExpanded - true if the TextView has been expanded
         */
        fun onExpandStateChanged(textView: TextView?, isExpanded: Boolean)
    }

    companion object {

        /* The default number of lines */
        private val MAX_COLLAPSED_LINES = 8

        /* The default animation duration */
        private val DEFAULT_ANIM_DURATION = 300

        /* The default content text size*/
        private val DEFAULT_CONTENT_TEXT_SIZE = 16f
        private val DEFAULT_CONTENT_TEXT_LINE_SPACING_MULTIPLIER = 1.0f

        private val STATE_TV_GRAVITY_LEFT = 0
        private val STATE_TV_GRAVITY_CENTER = 1
        private val STATE_TV_GRAVITY_RIGHT = 2

        private val isPostLolipop: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        private fun getDrawable(context: Context, @DrawableRes resId: Int): Drawable {
            val resources = context.resources
            return if (isPostLolipop) {
                resources.getDrawable(resId, context.theme)
            } else {
                resources.getDrawable(resId)
            }
        }

        private fun getRealTextViewHeight(textView: TextView): Int {
            val textHeight = textView.layout.getLineTop(textView.lineCount)
            val padding = textView.compoundPaddingTop + textView.compoundPaddingBottom
            return textHeight + padding
        }
    }
}
