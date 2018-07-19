package com.rosberry.arc.common.repository.persistence

import android.os.Bundle
import android.util.SparseArray
import android.util.SparseIntArray
import com.rosberry.arc.common.configuration.BundleConstants
import com.rosberry.arc.common.injection.scope.UiComponent
import com.rosberry.arc.common.presentation.ui.base.mvp.BasePresenter

 class AppCache {

    companion object {
        val presenters = SparseArray<BasePresenter<*, *, *>>()


        fun cachePresenter(bundle: Bundle, presenter: BasePresenter<*, *, *>) {
            bundle.putInt(BundleConstants.PRESENTER_ID, presenter.hashCode())
            AppCache.Companion.presenters.put(presenter.hashCode(), presenter)
        }


        fun injectPresenter(component: UiComponent, savedInstanceState: Bundle?, retainPresenter: Boolean): BasePresenter<*, *, *>? {
            if (retainPresenter) {
                val presenterId = savedInstanceState?.getInt(BundleConstants.PRESENTER_ID, 0) ?: 0
                if (presenterId == 0) {
                    component.inject()
                } else {
                    return AppCache.Companion.presenters.get(presenterId) as BasePresenter<*, *, *>
                }
            } else {
                component.inject()
            }
            return null
        }

    }


}