package com.rosberry.arc.common.sample.presentation.ui.debug


import com.rosberry.arc.common.presentation.ui.base.mvp.BaseInteractor
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage
import com.rosberry.arc.common.injection.scope.PerActivity
import javax.inject.Inject

/**
 * Created by Evgeniy Nagibin on 22/05/2017.
 */

@PerActivity
class DebugInteractor
@Inject constructor(internalStorage: InternalStorage) :
        BaseInteractor<DebugPresenter, DebugViewData>(internalStorage) {

    override fun onCreate(presenter: DebugPresenter, viewData: DebugViewData) {
        super.onCreate(presenter, viewData)
    }
}