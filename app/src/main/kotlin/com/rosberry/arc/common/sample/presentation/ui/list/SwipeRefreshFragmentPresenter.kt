package com.rosberry.arc.common.sample.presentation.ui.list

import com.rosberry.arc.common.presentation.ui.base.mvp.BaseFragmentPresenter
import com.rosberry.arc.common.presentation.ui.base.model.DialogModel
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.sample.presentation.model.BaseDataTrasformer
import com.rosberry.arc.common.sample.presentation.model.PlaceholderModel
import com.rosberry.arc.common.sample.presentation.ui.main.list.AwesomeModel
import com.rosberry.arc.common.sample.repository.model.ErrorModel
import javax.inject.Inject

/**
 * Created by dmitry on 08.06.17.
 */
class SwipeRefreshFragmentPresenter
@Inject constructor(viewData: SwipeRefreshViewData,
                    private val swipeRefreshInteractor: SwipeRefreshInteractor,
                    router: SwipeRefreshRouter) :
        BaseFragmentPresenter<SwipeRefreshView, SwipeRefreshViewData, SwipeRefreshRouter>(viewData, router),
        SwipeRefreshPresenter {


    fun refreshAwesome() {
        loadAwesome(true)
    }

    fun clickRetryAwesome() {
        loadAwesome(false)
    }

    fun clickAwesome(item: AwesomeModel) {
        message(viewData.getString(R.string.in_development))
    }

    fun loadAwesome(fromUser: Boolean) {
        checkSubscribtion(::refreshAwesome)?.apply {

            getView()?.setProgressVisible(!fromUser)

            if (!fromUser) {
                getView()?.setRefreshEnabled(false)
                getView()?.setPlaceholderVisible(false)
            }

            add(swipeRefreshInteractor.loadAwesome())
        }

    }

    override fun onCreate(view: SwipeRefreshView) {
        super.onCreate(view)
        swipeRefreshInteractor.onCreate(this, viewData)
    }

    override fun onViewCreated() {
        super.onViewCreated()

        subscribeWidget(v?.getRetryButtonObservable(), ::clickRetryAwesome)
        loadAwesome(false)
    }


    override fun onAwesomeLoadResult(arrayList: ArrayList<AwesomeModel>, errorModel: ErrorModel?) {
        getView()?.setProgressVisible(false)
        getView()?.setRefreshIndicatorVisible(false)
        getView()?.setRefreshEnabled(true)

        if (errorModel == null) {
            getView()?.setAwesome(BaseDataTrasformer.toAwesomeList(arrayList))
        } else {
            val placeholderModel = PlaceholderModel(errorModel)
            if (viewData.getAwesome().size == 0) {
                getView()?.setPlaceholder(placeholderModel)
                getView()?.setPlaceholderVisible(true)
            } else {
                getView()?.setPlaceholderVisible(false)
                message(placeholderModel.toMessage(viewData.context))
            }
        }
    }


    override fun message(text: String) {
        router.toastNavigator.show(DialogModel.Builder().content(text).build())
    }



}