package com.rosberry.arc.common.sample.presentation.ui.list


import com.rosberry.arc.common.presentation.ui.base.mvp.BaseInteractor
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage
import com.rosberry.arc.common.injection.scope.PerActivity
import com.rosberry.arc.common.presentation.ui.base.mvp.InteractorDataProvider
import com.rosberry.arc.common.presentation.ui.base.mvp.InteractorDataReceiver
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.sample.presentation.ui.main.MainInteractor
import com.rosberry.arc.common.sample.presentation.ui.main.list.AwesomeModel
import com.rosberry.arc.common.sample.repository.model.ErrorModel
import com.rosberry.arc.common.sample.usecase.auth.AuthUseCase
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Evgeniy Nagibin on 22/05/2017.
 */

class SwipeRefreshInteractor
@Inject constructor(internalStorage: InternalStorage, val authUseCase: AuthUseCase) :
        BaseInteractor<SwipeRefreshInteractor.SummainDataReceiver, SwipeRefreshInteractor.SubmainDataProvider>(internalStorage) {

    interface SubmainDataProvider : InteractorDataProvider {
        fun getAwesome(): ArrayList<AwesomeModel>

    }

    interface SummainDataReceiver : InteractorDataReceiver {
        fun onAwesomeLoadResult(arrayList: ArrayList<AwesomeModel>, errorModel: ErrorModel?)
    }

    fun loadAwesome(): Disposable {

        return authUseCase.login("", "").subscribe({
            dataProvider.getAwesome().apply {
                clear()
                addAll(ArrayList())
            }
            presenter.onAwesomeLoadResult(ArrayList<AwesomeModel>(), null)
        }, {
            presenter.onAwesomeLoadResult(ArrayList<AwesomeModel>(), ErrorModel(it))
        })
    }


}