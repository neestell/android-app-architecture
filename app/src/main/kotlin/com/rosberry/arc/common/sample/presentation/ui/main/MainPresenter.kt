package com.rosberry.arc.common.sample.presentation.ui.main

import com.rosberry.arc.common.presentation.ui.base.model.DialogModel
import com.rosberry.arc.common.presentation.ui.base.mvp.IBasePresenter

/**
 * Created by dmitry on 19.06.17.
 */
interface MainPresenter : IBasePresenter<MainViewData>, MainInteractor.CameraDataReceiver, PleasureInteractor.PleasureDataReceiver {
    fun message(it: DialogModel)
    fun changeCenterTextVisibility(visible: Boolean)
}