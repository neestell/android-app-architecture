package com.rosberry.arc.common.repository.text

import android.text.TextPaint

/**
 * Created by dmitry on 30.03.17.
 */

abstract class ClickableSpan : android.text.style.ClickableSpan() {

    var linkable: Boolean = false

    override fun updateDrawState(ds: TextPaint) {

        ds.color = ds.linkColor
        if (linkable) {
            ds.isUnderlineText = true
        }
    }
}
