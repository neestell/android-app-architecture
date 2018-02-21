package com.rosberry.arc.common.sample.presentation.ui.main

import android.os.Bundle
import com.rosberry.arc.common.presentation.ui.base.mvp.BaseRouter
import com.rosberry.arc.common.injection.scope.PerActivity
import com.rosberry.arc.common.sample.presentation.navigation.ActivityNavigator
import com.rosberry.arc.common.sample.presentation.navigation.DialogNavigator
import com.rosberry.arc.common.sample.presentation.navigation.FragmentNavigator
import com.rosberry.arc.common.sample.presentation.navigation.ToastNavigator
import com.rosberry.arc.common.sample.presentation.ui.submain.SubmainView
import javax.inject.Inject

/**
 * Created by dmitry on 07.02.2018.
 */
@PerActivity
class MainRouter
@Inject constructor(private val activityNavigator: ActivityNavigator,
                                     private val fragmentNavigator: FragmentNavigator,
                                     private val dialogNavigator: DialogNavigator,
                                     val toastNavigator: ToastNavigator): BaseRouter() {

    fun showTemporary(str: String) {
        fragmentNavigator.showTemporaryFragment(androidAdapter.fragmentManager, Bundle().apply { putString("key", str) })
    }

    fun deleteBottomFragment() {
        removeFragment(androidAdapter.fragmentManager, SubmainView.TAG)
    }

    fun showDebug() {
        fragmentNavigator.showDebug(androidAdapter.fragmentManager)
    }


}