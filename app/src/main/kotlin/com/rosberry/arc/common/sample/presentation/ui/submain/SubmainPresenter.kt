package com.rosberry.arc.common.sample.presentation.ui.submain

import com.rosberry.arc.common.presentation.ui.base.mvp.IBasePresenter
import com.rosberry.arc.common.sample.presentation.ui.debug.DebugInteractor

/**
 * Created by sergey on 11/8/17.
 */
interface SubmainPresenter : IBasePresenter<SubmainViewData>, SubmainInteractor.SummainDataReceiver, DebugInteractor.DebugDataReceiver