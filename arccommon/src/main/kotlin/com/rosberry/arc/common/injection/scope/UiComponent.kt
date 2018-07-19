package com.rosberry.arc.common.injection.scope

import com.rosberry.arc.common.presentation.ui.base.mvp.BasePresenter

interface UiComponent {

    fun inject()
    fun getUiPresenter(): BasePresenter<*, *, *>
}