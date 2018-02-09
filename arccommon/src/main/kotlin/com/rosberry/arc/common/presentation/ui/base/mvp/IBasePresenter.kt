package com.rosberry.arc.common.presentation.ui.base.mvp

import com.rosberry.arc.common.repository.persistence.internal.ViewDataRepository

/**
 * Created by neestell on 9/28/16.
 */

interface IBasePresenter<D: ViewDataRepository>{
    fun getFrameworkAdapter():FrameworkAdapter<D>
    fun getView(): BaseView?
}
