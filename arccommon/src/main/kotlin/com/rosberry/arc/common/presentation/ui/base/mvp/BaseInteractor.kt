package com.rosberry.arc.common.presentation.ui.base.mvp

import android.app.Activity
import android.os.Handler
import com.rosberry.arc.common.presentation.ui.base.model.DialogModel
import com.rosberry.arc.common.presentation.ui.base.model.PermissionModel
import com.rosberry.arc.common.repository.Callback
import com.rosberry.arc.common.repository.persistence.internal.ViewDataRepository
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage


/**
 * Created by dmitry on 30.03.17.
 */

abstract class BaseInteractor<P : IBasePresenter<D>, D : ViewDataRepository>(val storage: InternalStorage) {
    protected lateinit var presenter: P
    protected lateinit var viewData: D
    protected val handler = Handler()
    private var permissionController: PermissionController? = null;

    open fun onCreate(presenter: P, viewData: D) {
        this.presenter = presenter
        this.viewData = viewData
        if (presenter.getView() is Activity) this.permissionController = PermissionController(presenter.getView());
    }

    fun onComplete() {

    }

    fun requestAccess(model: PermissionModel, accept: () -> Unit,  declined: (m: DialogModel) -> Unit) {
        if (permissionController?.checkPermissions(model.name)?: false) {
            accept()
        } else {
            permissionController?.requestPermissions(object : Callback<Boolean> {

                override fun onResult(value: Boolean) {
                    if (value) {
                        accept()
                    } else {
                        declined(DialogModel.Builder().contentId(model.noGrantedMsgId).build())
                    }
                }

            }, model.name)
        }
    }

    fun calculateMultiplecation(param1: Int, param2: Int) = param1 * param2


}
