package com.rosberry.arc.common.sample.presentation

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.rosberry.arc.common.sample.injection.DIHelper

/**
 * Created by dmitry on 05.02.2018.
 */
class App: MultiDexApplication() {

    companion object {

        init {
            System.loadLibrary("native-lib")
        }
    }

    val diHelper =  DIHelper()


    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        setupDagger()

    }

    private fun setupDagger() {
        diHelper.initApplicationComponent(this)
    }
}