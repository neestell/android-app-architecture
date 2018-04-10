package com.rosberry.arc.common.presentation.ui.base.mvp

import com.rosberry.arc.common.presentation.ui.base.model.DialogModel

interface InteractorDataReceiver {
    fun getFrameworkAdapter(): FrameworkAdapter
    fun getView(): BaseView?
    fun message(text: String){

    }
}