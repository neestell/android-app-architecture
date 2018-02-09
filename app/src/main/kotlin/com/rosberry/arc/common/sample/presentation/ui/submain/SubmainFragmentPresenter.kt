package com.rosberry.arc.common.sample.presentation.ui.submain

import com.rosberry.arc.common.presentation.ui.base.mvp.BaseFragmentPresenter
import com.rosberry.arc.common.injection.scope.PerActivity
import javax.inject.Inject

/**
 * Created by dmitry on 08.06.17.
 */
@PerActivity
class SubmainFragmentPresenter
@Inject constructor(viewData: SubmainViewData, private val subMainInteractor: SubmainInteractor, router: SubMainRouter) :
        BaseFragmentPresenter<SubmainView, SubmainViewData, SubMainRouter>(viewData, router), SubmainPresenter {


    override fun onCreate(view: SubmainView) {
        super.onCreate(view)
        subMainInteractor.onCreate(this, viewData)
    }
}