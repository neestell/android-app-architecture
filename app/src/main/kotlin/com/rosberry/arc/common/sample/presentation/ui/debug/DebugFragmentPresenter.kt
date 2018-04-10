package com.rosberry.arc.common.sample.presentation.ui.debug

import com.rosberry.arc.common.presentation.ui.base.mvp.BaseFragmentPresenter
import com.rosberry.arc.common.injection.scope.PerActivity
import javax.inject.Inject

/**
 * Created by dmitry on 08.06.17.
 */
@PerActivity
class DebugFragmentPresenter
@Inject constructor(viewData: DebugViewData, private val debugInteractor: DebugInteractor, router: DebugMainRouter) :
        BaseFragmentPresenter<DebugView, DebugViewData, DebugMainRouter>(viewData, router), DebugPresenter, DebugInteractor.DebugDataReceiver {


    override fun onCreate(view: DebugView) {
        super.onCreate(view)
        debugInteractor.onCreate(this, viewData)
    }
}