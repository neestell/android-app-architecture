package com.rosberry.arc.common.repository.text

import android.support.annotation.ColorRes
import android.support.annotation.FontRes
import android.support.annotation.StringRes

import com.rosberry.arc.common.repository.Callback

/**
 * Created by neestell on 09.01.17.
 */

class SpanModel {

    var text: String? = null
    var typeface = -1
    var color = -1
    var textSize = -1f
    var listener: Callback<Boolean>? = null
    var isLinkable = true

    fun hasListener() = listener != null


    class Builder {

        private val spanModel = SpanModel()

        fun text(text: String): Builder {
            spanModel.text = text
            return this
        }

        fun typeface(@FontRes typeface: Int): Builder {
            spanModel.typeface = typeface
            return this
        }

        fun color(@ColorRes color: Int): Builder {
            spanModel.color = color
            return this
        }

        fun size(value: Int): Builder {
            spanModel.textSize = value.toFloat()
            return this
        }

        fun clickListener(listener: Callback<Boolean>): Builder {
            spanModel.listener = listener
            return this
        }

        fun linkable(b: Boolean): Builder {
            spanModel.isLinkable = b

            return this
        }

        fun build() = spanModel

    }
}
