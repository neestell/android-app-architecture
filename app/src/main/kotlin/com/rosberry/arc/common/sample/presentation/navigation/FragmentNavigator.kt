package com.rosberry.arc.common.sample.presentation.navigation

import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.rosberry.arc.common.repository.extensions.getByTag
import com.rosberry.arc.common.repository.extensions.removeFragmentByTag
import com.rosberry.arc.common.repository.extensions.showFragment
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.sample.presentation.ui.submain.SubmainFragment
import kotlinx.android.synthetic.main.a_main.view.*
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

}