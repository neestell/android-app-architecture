package com.rosberry.arc.common.repository.log

import android.util.Log
import com.rosberry.arc.common.BuildConfig


object LogUtil {

    private val ENABLED = BuildConfig.DEBUG
    private val TAG = "MyDebugApplication"

    fun d(o: Any, msg: String?) {
        if (!ENABLED) return

        val tag = String.format("%s%s", TAG, o.javaClass.name)

        Log.d(tag, if (msg == null) "Empty messge" else (msg + ""))
    }

    fun i(o: Any, msg: String?) {

        val tag = String.format("%s%s", TAG, o.javaClass.name)

        Log.i(tag, if (msg == null) "Empty messge" else (msg + ""))

    }
}
