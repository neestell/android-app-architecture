package com.rosberry.arc.common.presentation.ui.base.view

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

/**
 * Created by Evgeniy Nagibin on 04/08/2017.
 */
class SquareImageView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}