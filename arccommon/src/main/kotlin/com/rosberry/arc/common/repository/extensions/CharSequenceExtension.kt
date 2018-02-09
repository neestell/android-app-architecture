package com.rosberry.arc.common.repository.extensions

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import com.rosberry.arc.common.repository.text.ClickableSpan
import com.rosberry.arc.common.repository.text.CustomTypefaceSpan
import com.rosberry.arc.common.repository.text.SpanModel
import com.rosberry.arc.common.repository.text.TextUtils

/**
 * Created by dmitry on 08.06.17.
 */

fun CharSequence.spanText(context: Context, vararg spanModels: SpanModel): CharSequence {
    val ss = SpannableString(this)
    for (spanModel in spanModels) {

        val startIndex = this.indexOf(spanModel.text!!)
        val endIndex = startIndex + spanModel.text!!.length

        if (spanModel.typeface != -1)
            ss.setSpan(CustomTypefaceSpan("", ResourcesCompat.getFont(context, spanModel.typeface)!!),
                    startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        if (spanModel.textSize != -1f) {
            ss.setSpan(AbsoluteSizeSpan(spanModel.textSize.toInt(), true), startIndex, endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        if (spanModel.color != -1)
            ss.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, spanModel.color)), startIndex,
                    endIndex, 0)

        if (spanModel.hasListener()) {
            val clickableSpan = object : ClickableSpan() {

                override fun onClick(view: View) {
                    spanModel.listener!!.onResult(true)
                }
            }
            clickableSpan.linkable = spanModel.isLinkable
            ss.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        }
    }

    return ss
}


