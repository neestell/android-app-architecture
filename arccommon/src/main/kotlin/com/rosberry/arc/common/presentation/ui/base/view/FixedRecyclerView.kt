package com.rosberry.arc.common.presentation.ui.base.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent



/**
 * Created by sergey on 12/21/17.
 */
class FixedRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        val requestCancelDisallowInterceptTouchEvent = scrollState == RecyclerView.SCROLL_STATE_SETTLING
        val consumed = super.onInterceptTouchEvent(event)
        val action = event.actionMasked

        when (action) {
            MotionEvent.ACTION_DOWN -> if (requestCancelDisallowInterceptTouchEvent) {
                parent.requestDisallowInterceptTouchEvent(false)

                // only if it touched the top or the bottom. Thanks to @Sergey's answer.
                if (!canScrollVertically(-1) || !canScrollVertically(1)) {
                    // stop scroll to enable child view to get the touch event
                    stopScroll()
                    // do not consume the event
                    return false
                }
            }
        }

        return consumed
    }
}