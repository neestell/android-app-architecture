package com.rosberry.arc.common.presentation.ui.base.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

/**
 * Created by dmitry on 23.11.2017.
 */
class SquareRelativeLayout: RelativeLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, measuredWidth)

    }

}