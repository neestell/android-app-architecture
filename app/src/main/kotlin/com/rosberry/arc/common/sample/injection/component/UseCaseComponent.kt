package com.rosberry.arc.common.sample.injection.component

import com.rosberry.arc.common.injection.scope.PerActivity
import com.rosberry.arc.common.sample.presentation.ui.debug.DebugFragment
import com.rosberry.arc.common.sample.presentation.ui.list.SwipeRefreshFragment
import com.rosberry.arc.common.sample.presentation.ui.main.MainActivity
import com.rosberry.arc.common.sample.presentation.ui.submain.SubmainFragment


import dagger.Component


/**
 * Created by dmitry on 06.06.17.
 */
@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface UseCaseComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: SubmainFragment)
    fun inject(fragment: DebugFragment)
    fun inject(fragment: SwipeRefreshFragment)

}