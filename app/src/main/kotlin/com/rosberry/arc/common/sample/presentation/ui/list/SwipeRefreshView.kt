package com.rosberry.arc.common.sample.presentation.ui.list

import android.view.View
import com.rosberry.arc.common.presentation.ui.base.mvp.BaseView
import com.rosberry.arc.common.sample.presentation.ui.base.SwipeRefresh
import com.rosberry.arc.common.sample.presentation.ui.main.list.AwesomeModel


/**
 * Created by sergey on 11/8/17.
 */
interface SwipeRefreshView : BaseView, SwipeRefresh {

    companion object {
        val TAG = "swipe_refresh_view"
    }

    fun setAwesome(awesome: ArrayList<AwesomeModel>)

}