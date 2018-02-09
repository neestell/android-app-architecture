package com.rosberry.arc.common.presentation.ui.base.model

import android.support.annotation.MenuRes
import android.support.annotation.StringRes

/**
 * Created by neestell on 06.09.16.
 */
open class FragmentModel : ViewModel() {
    var retainInstance = true
    var hasOptionMenu = true
    var hasToolbar = false
    @MenuRes var menu = -1
    @StringRes var titleId = -1
}
