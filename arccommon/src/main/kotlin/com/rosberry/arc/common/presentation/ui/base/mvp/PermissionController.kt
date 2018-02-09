package com.rosberry.arc.common.presentation.ui.base.mvp

import com.rosberry.arc.common.presentation.ui.base.BaseActivity
import com.rosberry.arc.common.repository.Callback
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * Created by dmitry on 08.02.2018.
 */
class PermissionController constructor(view: BaseView?) {
    private var rxPermissions: RxPermissions? = null

    init {
        rxPermissions = RxPermissions(view as BaseActivity<*, *>)
    }

    fun requestPermissions(callback: Callback<Boolean>, vararg permissions: String) {
        rxPermissions?.request(*permissions)?.subscribe({ callback.onResult(it) }, { callback.onResult(false) })
    }

    fun checkPermissions(vararg permissions: String): Boolean {
        return permissions.all { it -> if (rxPermissions != null) rxPermissions!!.isGranted(it) else false }
    }

}