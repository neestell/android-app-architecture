package com.rosberry.arc.common.sample.presentation.ui.submain


import com.rosberry.arc.common.presentation.ui.base.mvp.BaseInteractor
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage
import com.rosberry.arc.common.injection.scope.PerActivity
import com.rosberry.arc.common.presentation.ui.base.mvp.InteractorDataProvider
import com.rosberry.arc.common.presentation.ui.base.mvp.InteractorDataReceiver
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.sample.presentation.ui.main.MainInteractor
import javax.inject.Inject

/**
 * Created by Evgeniy Nagibin on 22/05/2017.
 */

class SubmainInteractor
@Inject constructor(internalStorage: InternalStorage) :
        BaseInteractor<SubmainInteractor.SummainDataReceiver, SubmainInteractor.SubmainDataProvider>(internalStorage) {

    interface SubmainDataProvider: InteractorDataProvider {

    }

    interface SummainDataReceiver: InteractorDataReceiver{

    }

    override fun onCreate(presenter: SummainDataReceiver, viewData: SubmainDataProvider) {
        super.onCreate(presenter, viewData)
    }
}