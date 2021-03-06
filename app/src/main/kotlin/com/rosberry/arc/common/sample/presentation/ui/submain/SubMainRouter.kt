package com.rosberry.arc.common.sample.presentation.ui.submain


import com.rosberry.arc.common.presentation.ui.base.mvp.BaseRouter
import com.rosberry.arc.common.sample.presentation.navigation.ActivityNavigator
import com.rosberry.arc.common.sample.presentation.navigation.FragmentNavigator
import com.rosberry.arc.common.sample.presentation.navigation.DialogNavigator
import com.rosberry.arc.common.sample.presentation.navigation.ToastNavigator

import javax.inject.Inject

/**
 * Created by dmitry on 07.02.2018.
 */
class SubMainRouter
@Inject constructor(activityNavigator: ActivityNavigator,
                                        fragmentNavigator: FragmentNavigator,
                                        dialogNavigator: DialogNavigator,
                                        toastNavigator: ToastNavigator): BaseRouter() {

}