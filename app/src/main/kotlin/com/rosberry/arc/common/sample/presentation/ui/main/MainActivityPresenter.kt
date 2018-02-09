package com.rosberry.arc.common.sample.presentation.ui.main

import com.rosberry.arc.common.presentation.ui.base.model.DialogModel
import com.rosberry.arc.common.presentation.ui.base.mvp.BasePresenter
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.injection.scope.PerActivity
import com.rosberry.arc.common.sample.presentation.ui.submain.SubmainView
import javax.inject.Inject


/**
 * Created by dmitry on 08.06.17.
 */
@PerActivity
class MainActivityPresenter
@Inject constructor(viewData: MainViewData, mainRouter: MainRouter, val mainInteractor: MainInteractor)
    : BasePresenter<MainView, MainViewData, MainRouter>(viewData, mainRouter), MainPresenter, BasePresenter.Host {

    fun clickHardBack() {
        router.finish(getView())
    }

    fun clickSoftBack() {
        router.finish(getView())
    }

    fun clickOptionDelete() {
        router.deleteBottomFragment()
    }

    fun clickOptionHelp() {
        if (findChildPresenter(SubmainView.TAG) != null) {
            router.toastNavigator
                    .show(DialogModel.Builder()
                            .contentId(R.string.help_yoursel)
                            .build())
        } else{
            router.showTemporary("Text")
        }

    }

    override fun onCreate(view: MainView) {
        super.onCreate(view)
        mainInteractor.onCreate(this, viewData)
        mainInteractor.showCamera();
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun changeCenterText(str: String) {
        v?.setCenterText(str)
    }


    override fun showViewCreated(msg: String) {
        router.toastNavigator
                .show(DialogModel.Builder()
                        .content(msg)
                        .build())
    }

    override fun onMainViewCreated(str: String) {
        router.showTemporary(str);
    }

    override fun message(it: DialogModel) {
        router.toastNavigator.show(it)
    }

}
