package com.rosberry.arc.common.presentation.ui.base.mvp

import com.rosberry.arc.common.repository.persistence.internal.ViewDataRepository

/**
 * Created by neestell on 9/28/16.
 */

abstract class BaseFragmentPresenter<VI : BaseView, D : ViewDataRepository, RO: BaseRouter>(viewData: D, router: RO)
    : BasePresenter<VI, D, RO>(viewData, router) {

    open fun onViewCreated() {
        addChildPresenter()
    }

    fun onViewDestroyed() {
        removeChildPresenter()
    }


    private fun removeChildPresenter() {
        if (v != null) {
            val tag = v?.getViewTag()
            if (v?.getParentView() != null && v?.getParentView() is BaseView.Host)
                v?.getParentView()?.removeChildPresenter(tag!!, this)
        }
    }

    private fun addChildPresenter() {
        if (v != null) {
            val tag = v?.getViewTag()
            if (v?.getParentView() != null && v?.getParentView() is BaseView.Host)
                v?.getParentView()?.addChildPresenter(tag!!, this)
        }

    }

}
