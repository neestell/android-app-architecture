package com.rosberry.arc.common.presentation.ui.base.mvp

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle

/**
 * Created by neestell on 9/28/16.
 */

interface BaseView {

    fun getIntent(): Intent
    fun getResources(): Resources
    fun getArguments(): Bundle?

    fun exists(): Boolean

    fun getViewTag(): String

    fun getParentView(): Host?

    interface Host {
        fun addChildPresenter(viewTag: String, presenter: BaseFragmentPresenter<*, *, *>)
        fun removeChildPresenter(viewTag: String, presenter: BaseFragmentPresenter<*, *, *>)

    }
}
