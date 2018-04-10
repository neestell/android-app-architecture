package com.rosberry.arc.common.sample.presentation.ui.main

import android.Manifest
import com.rosberry.arc.common.presentation.ui.base.model.PermissionModel
import com.rosberry.arc.common.presentation.ui.base.mvp.BaseInteractor
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.injection.scope.PerActivity
import com.rosberry.arc.common.presentation.ui.base.mvp.InteractorDataProvider
import com.rosberry.arc.common.presentation.ui.base.mvp.InteractorDataReceiver
import com.rosberry.arc.common.sample.repository.jni.JNIAdapter
import com.rosberry.arc.common.sample.usecase.auth.AuthUseCase
import javax.inject.Inject

/**
* Created by dmitry on 22/05/2017.
*/

class PleasureInteractor
@Inject constructor(storage: InternalStorage, val mainInteractor: MainInteractor) :
        BaseInteractor<PleasureInteractor.PleasureDataReceiver, PleasureInteractor.PleasureDataProvider>(storage) {

    interface PleasureDataProvider: InteractorDataProvider {

    }

    interface PleasureDataReceiver: InteractorDataReceiver {

    }

    override fun onCreate(presenter: PleasureDataReceiver, viewData: PleasureDataProvider) {
        super.onCreate(presenter, viewData)
        calculateFunction()
    }

    fun calculateFunction(){
        val sum = 1 + 1
        mainInteractor.calculateMultiplecation(sum, sum)
    }


}