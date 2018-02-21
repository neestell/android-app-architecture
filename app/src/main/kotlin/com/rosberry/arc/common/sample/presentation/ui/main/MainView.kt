package com.rosberry.arc.common.sample.presentation.ui.main

import android.view.View
import com.rosberry.arc.common.presentation.ui.base.mvp.BaseView
import io.reactivex.Observable


/**
 * Created by dmitry on 08.06.17.
 */
interface MainView : BaseView, BaseView.Host {

    companion object {
        val TAG = "mainview"
    }

    fun setCenterText(string: String)
    fun setCenterTextVisible(visible: Boolean)

    fun getTakeShotObservable(): Observable<Any>
}