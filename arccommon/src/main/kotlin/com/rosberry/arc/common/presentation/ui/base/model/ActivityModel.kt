package com.rosberry.arc.common.presentation.ui.base.model

import android.support.annotation.*
import com.rosberry.arc.common.R


/**
 * Created by neestell on 06.09.16.
 */
class ActivityModel : ViewModel() {
    var hasActionBar = false
    var hasBack = false

    @IdRes val toolbarId: Int = R.id.toolbar
    @MenuRes var menu: Int = -1
    @StringRes var title: Int = -1
    @FontRes var titleFont: Int = R.font.roboto_medium

}
