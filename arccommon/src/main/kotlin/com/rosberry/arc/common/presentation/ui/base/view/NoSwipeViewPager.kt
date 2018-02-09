package com.rosberry.arc.common.presentation.ui.base.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent

/**
 * Created by sergey on 11/10/17.
 */
class NoSwipeViewPager : ViewPager {
    private var isSwipeEnabled = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        isSwipeEnabled = false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (isSwipeEnabled) {
            super.onTouchEvent(event)
        } else false

    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (isSwipeEnabled) {
            super.onInterceptTouchEvent(event)
        } else false

    }

    fun setPagingEnabled(enabled: Boolean) {
        isSwipeEnabled = enabled
    }

    override fun executeKeyEvent(event: KeyEvent): Boolean {
        return if (isSwipeEnabled) super.executeKeyEvent(event) else false
    }
}