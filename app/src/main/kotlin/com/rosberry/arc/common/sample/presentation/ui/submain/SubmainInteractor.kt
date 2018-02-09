package com.rosberry.arc.common.sample.presentation.ui.submain


import com.rosberry.arc.common.presentation.ui.base.mvp.BaseInteractor
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage
import com.rosberry.arc.common.injection.scope.PerActivity
import javax.inject.Inject

/**
 * Created by Evgeniy Nagibin on 22/05/2017.
 */

@PerActivity
class SubmainInteractor
@Inject constructor(internalStorage: InternalStorage) :
        BaseInteractor<SubmainPresenter, SubmainViewData>(internalStorage) {

    override fun onCreate(presenter: SubmainPresenter, viewData: SubmainViewData) {
        super.onCreate(presenter, viewData)
    }
}