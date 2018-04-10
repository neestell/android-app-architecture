package com.rosberry.arc.common.sample.presentation.ui.debug


import com.rosberry.arc.common.presentation.ui.base.mvp.BaseInteractor
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage
import com.rosberry.arc.common.injection.scope.PerActivity
import com.rosberry.arc.common.presentation.ui.base.mvp.InteractorDataProvider
import com.rosberry.arc.common.presentation.ui.base.mvp.InteractorDataReceiver
import javax.inject.Inject

/**
 * Created by Evgeniy Nagibin on 22/05/2017.
 */

@PerActivity
class DebugInteractor
@Inject constructor(internalStorage: InternalStorage) :
        BaseInteractor<DebugInteractor.DebugDataReceiver, DebugInteractor.DebugDataProvider>(internalStorage) {

    interface DebugDataProvider: InteractorDataProvider {

    }

    interface DebugDataReceiver: InteractorDataReceiver {

    }

    override fun onCreate(presenter: DebugDataReceiver, viewData: DebugDataProvider) {
        super.onCreate(presenter, viewData)
        presenter.getView()
    }
}