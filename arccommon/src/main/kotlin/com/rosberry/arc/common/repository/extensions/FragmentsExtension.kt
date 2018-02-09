package com.rosberry.arc.common.repository.extensions

import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * Created by dmitry on 07.06.17.
 */

fun FragmentManager.showFragment(f: Fragment, frameId: Int, stack: Boolean = false, tag: String, anim: IntArray?) {

    val transaction = beginTransaction()
    setAnimation(anim, transaction)
    transaction.replace(frameId, f, tag)

    if (stack) transaction.addToBackStack(tag)

    transaction.commitAllowingStateLoss()
}

fun Fragment.showFragment(fm: FragmentManager, frameId: Int, stack: Boolean = false, tag: String, anim: IntArray?) {
    fm.showFragment(this, frameId, stack, tag, anim)
}

private fun setAnimation(anim: IntArray?, transaction: FragmentTransaction) {
    var animation = anim
    if (animation != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
        if (animation.isEmpty()) {
            animation = intArrayOf(android.R.anim.fade_in, android.R.anim.fade_out,
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
        if (animation.size == 2)
            transaction.setCustomAnimations(animation[0], animation[1])
        else
            transaction.setCustomAnimations(animation[0], animation[1], animation[2], animation[3])
    }
}

fun FragmentManager.popFragmentByTag(tag: String) = this.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)

fun FragmentManager.getByTag(tag: String) = this.findFragmentByTag(tag)

fun FragmentManager.removeFragmentByTag(tag: String) {
    val ft = this.beginTransaction()
    val prev = this.findFragmentByTag(tag)
    if (prev != null) {
        ft.remove(prev)
        ft.commitAllowingStateLoss()
    }
}

fun FragmentManager.clearBackStack() {
    if (this.backStackEntryCount > 0) {
        if (this.getBackStackEntryAt(0) != null)
            this.popBackStack(this.getBackStackEntryAt(0).id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

fun FragmentManager.hasFragments(): Boolean {
    if (this.fragments == null)
        return true
    val fr = this.fragments
    if (fr.size > 0) {
        val allNull = fr.none { it != null }
        return allNull
    } else {
        return true
    }
}

