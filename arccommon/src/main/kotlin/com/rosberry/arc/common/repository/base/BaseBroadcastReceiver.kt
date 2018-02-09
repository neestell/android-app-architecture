package com.rosberry.arc.common.repository.base

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter

/**
 * Created by neestell on 18.12.15.
 */
abstract class BaseBroadcastReceiver : BroadcastReceiver() {

    protected fun checkIntent(intent: Intent?, vararg action: String): Boolean {

        return intent != null && intent.action != null && checkActions(intent, action)
    }

    protected fun checkIntent(intent: Intent?, vararg filters: IntentFilter): Boolean {

        return intent != null && intent.action != null && checkActions(intent, filters)
    }

    fun checkActions(intent: Intent, actions: Array<out IntentFilter>): Boolean {
        for (filter in actions) {
            (0..filter.countActions() - 1)
                    .filter { filter.getAction(it) == intent.action }
                    .forEach { return true }
        }
        return false
    }

    fun checkActions(intent: Intent, actions: Array<out String>): Boolean {
        return actions.any { it == intent.action }
    }

    abstract val isGeneral: Boolean

}
