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
@Inject constructor(viewData: MainViewData, mainRouter: MainRouter,
                    val mainInteractor: MainInteractor, //Interactors add to end of the injection
                    val pleasureInteractor: PleasureInteractor
) : BasePresenter<MainView, MainViewData, MainRouter>(viewData, mainRouter), MainPresenter, BasePresenter.Host {


    /*--Start interaction methods. Named starting from physical actionsaction(click, press, selected etc)--*/

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
        } else {
            router.showTemporary("Text")
        }

    }

    fun clickLoadStuff() {
        checkSubscribtion(::clickLoadStuff)?.add(mainInteractor.auth())
    }


    fun clickTakeShot() {
        message(DialogModel.Builder()
                .contentId(R.string.taking_shot)
                .build())
        mainInteractor.fakeShot();

    }

    fun clickDebug() {
        router.showDebug()
    }

    /*--End interaction methods. Named starting from physical actionsaction(click, press, selected etc)--*/

    /*--Start lifecycle actions--*/

    override fun onCreate(view: MainView) {
        super.onCreate(view)
        mainInteractor.onCreate(this, viewData)
        pleasureInteractor.onCreate(this, viewData)
        mainInteractor.showCamera();
        subscribeWidget(v?.getTakeShotObservable(), ::clickTakeShot, true)
        subscribeWidget(v?.getTakeShotDebugObservable(), ::clickDebug)
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    /*--end lifecycle actions--*/

    /*--Start public events actions starting from on prefix--*/

    override fun onMainViewCreated(str: String) {
        router.showTemporary(str);
    }

    override fun onAuthorized() {

    }

    override fun onShotTaken() {
        subscribeWidget(v?.getTakeShotObservable(), ::clickTakeShot, true)
        message(DialogModel.Builder()
                .contentId(R.string.shot_has_been_taken)
                .build())
    }

    /*--End public events actions starting  with ON prefix--*/

    /*--Start view data change methods and its visibility starting from verb change, --*/

    override fun changeCenterText(str: String) {
        v?.setCenterText(str) // this methods may contain a list of setters for view
    }

    override fun changeCenterTextVisibility(visible: Boolean) {
        v?.setCenterTextVisible(visible)
    }
    /*--End view data change methods and its visibility starting from verb change, --*/


    /*--Start methods to show dialog, toasts, activities --*/

    override fun showViewCreated(msg: String) {
        router.toastNavigator
                .show(DialogModel.Builder()
                        .content(msg)
                        .build())
    }


    override fun message(it: DialogModel) {
        router.toastNavigator.show(it)
    }
    /*--End methods to show dialog, toasts, activities --*/
    override fun setLoading(b: Boolean) {

    }

}
