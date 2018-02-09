package com.rosberry.arc.common.presentation.ui.base.mvp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.FragmentManager
import com.rosberry.arc.common.repository.persistence.internal.ViewDataRepository
import javax.inject.Inject

/**
 * Created by dmitry on 25.12.2017.
 */
class FrameworkAdapter<out D: ViewDataRepository> @Inject constructor(val viewData: D) {
    private val activityResultCallbacks: HashSet<OnActivityResult> = HashSet()
    lateinit var fragmentManager:FragmentManager
    private var handler = Handler()

    fun saveInstanceState(bundle: Bundle?) {
        viewData.saveInstanceState(bundle)
    }

    fun restoreInstanceState(bundle: Bundle?) {
        viewData.restoreInstanceState(bundle)
    }

    fun addActivityResultListener(listener: OnActivityResult){
        activityResultCallbacks.add(listener)
    }

    fun removeActivityResultListener(listener: OnActivityResult){
        activityResultCallbacks.remove(listener)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        for (item in activityResultCallbacks){
            item.onActivityResult(requestCode, resultCode, data)
        }
    }

    interface OnActivityResult {
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }

    fun attachFragmentManager(supportFragmentManager: FragmentManager?) {
        fragmentManager = supportFragmentManager!!;
    }
}