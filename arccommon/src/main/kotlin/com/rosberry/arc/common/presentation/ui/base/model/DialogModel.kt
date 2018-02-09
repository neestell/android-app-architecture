package com.rosberry.arc.common.presentation.ui.base.model

import android.support.annotation.StringRes
import com.rosberry.arc.common.repository.Callback

/**
 * Created by dmitry on 30.10.17.
 */
class DialogModel {
    var title: Int = 0
        private set
    var content: CharSequence = ""
        private set
    var contentId: Int = 0
        private set
    var positive: Int = 0
        private set
    var negative: Int = 0
        private set
    var neutral: Int = 0
        private set

    var positiveListener: Callback<Boolean>? = null
        private set
    var negativeListener: Callback<Boolean>? = null
        private set
    var neutralListener: Callback<Boolean>? = null
        private set
    var dismissListener: Callback<String>? = null
        private set

    var isCancelable: Boolean = false
        private set

    val max: Int
        get() = 100

    class Builder {

        private val dialogModel = DialogModel()

        fun title(title: Int): Builder {
            dialogModel.title = title

            return this
        }

        fun content(content: CharSequence): Builder {
            dialogModel.content = content

            return this
        }

        fun contentId(@StringRes contentId: Int): Builder {
            dialogModel.contentId = contentId

            return this
        }

        fun positive(positive: Int): Builder {
            dialogModel.positive = positive

            return this
        }

        fun negative(negative: Int): Builder {
            dialogModel.negative = negative

            return this
        }

        fun neutral(neutral: Int): Builder {
            dialogModel.neutral = neutral

            return this
        }

        fun positiveListener(listener: Callback<Boolean>): Builder {
            dialogModel.positiveListener = listener

            return this
        }

        fun negativeListener(listener: Callback<Boolean>): Builder {
            dialogModel.negativeListener = listener

            return this
        }

        fun neutralListener(listener: Callback<Boolean>): Builder {
            dialogModel.neutralListener = listener

            return this
        }

        fun dismissListener(listener: Callback<String>): Builder {
            dialogModel.dismissListener = listener

            return this
        }

        fun cancellable(b: Boolean): Builder {
            dialogModel.isCancelable = b

            return this
        }

        fun build(): DialogModel {
            return dialogModel
        }

    }
}