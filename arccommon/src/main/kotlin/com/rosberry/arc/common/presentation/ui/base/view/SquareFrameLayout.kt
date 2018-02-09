package com.rosberry.arc.common.presentation.ui.base.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by dmitry on 15.01.2018.
 */
class SquareFrameLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth);

    }
}