package com.rosberry.arc.common.sample.presentation.navigation

import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.rosberry.arc.common.repository.extensions.getByTag
import com.rosberry.arc.common.repository.extensions.showFragment
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.sample.presentation.ui.debug.DebugFragment
import com.rosberry.arc.common.sample.presentation.ui.submain.SubmainFragment
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by neestell on 06.09.16.
 */
@Singleton
class FragmentNavigator @Inject constructor() {
    val defaultAnim = intArrayOf(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)

    fun showTemporaryFragment(fm: FragmentManager, bundle: Bundle?) {
        fm.getByTag(SubmainFragment.TAG)
                ?: SubmainFragment.newInstance(bundle)
                        .showFragment(fm, R.id.content_view, false, SubmainFragment.TAG, defaultAnim)
    }

    fun showDebug(fm: FragmentManager) {
        DebugFragment.newInstance().show(fm, DebugFragment.TAG)
    }

}