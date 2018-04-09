package com.rosberry.arc.common.sample.presentation.ui.main

import android.Manifest
import com.rosberry.arc.common.presentation.ui.base.model.PermissionModel
import com.rosberry.arc.common.presentation.ui.base.mvp.BaseInteractor
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.injection.scope.PerActivity
import com.rosberry.arc.common.sample.repository.jni.JNIAdapter
import com.rosberry.arc.common.sample.usecase.auth.AuthUseCase
import javax.inject.Inject

/**
 * Created by Evgeniy Nagibin on 22/05/2017.
 */

@PerActivity
class MainInteractor
@Inject constructor(storage: InternalStorage, authUseCase: AuthUseCase) :
        BaseInteractor<MainPresenter, MainViewData>(storage) {


    override fun onCreate(presenter: MainPresenter, viewData: MainViewData) {
        super.onCreate(presenter, viewData)
        parseBundle()
        presenter.showViewCreated(viewData.getString(R.string.view_created));
        presenter.onMainViewCreated("Model or primitive types")
        presenter.getFrameworkAdapter()

    }

    private fun parseBundle() {
        val boolka = presenter.getFrameworkAdapter().getIntent()?.getBooleanExtra("boolka", false)
    }

    fun showCamera() {

        val body = {
            presenter.changeCenterText(JNIAdapter.stringFromJNI());
        }

        requestAccess(PermissionModel(Manifest.permission.CAMERA, R.string.access_camera_declined),
                body,
                { presenter.message(it) })
    }

    fun deleteFragment() {

    }

    fun deleteBottomFragment() {

    }

    fun fakeShot() {
        handler.postDelayed({ presenter.onShotTaken() }, 5000)
    }


}