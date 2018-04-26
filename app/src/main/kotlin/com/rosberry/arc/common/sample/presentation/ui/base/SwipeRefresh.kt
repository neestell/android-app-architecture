package com.rosberry.arc.common.sample.presentation.ui.base

import android.support.v4.widget.SwipeRefreshLayout
import com.rosberry.arc.common.sample.presentation.model.PlaceholderModel
import io.reactivex.Observable


interface SwipeRefresh: SwipeRefreshLayout.OnRefreshListener {

    fun setProgressVisible(b: Boolean)
    fun setPlaceholderVisible(b: Boolean)
    fun setRefreshEnabled(b: Boolean)
    fun setRefreshIndicatorVisible(b: Boolean)
    fun setPlaceholder(placeholderModel: PlaceholderModel)

    fun getRetryButtonObservable(): Observable<Any>

}