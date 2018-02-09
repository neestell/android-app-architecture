package com.rosberry.arc.common.sample.injection.module

import android.content.Context
import dagger.Module

/**
 * Created by dmitry on 08.11.2017.
 */
@Module
class ExternalStorageModule(val context: Context) {

    companion object {
        private val TAG_STORAGE = "common"
    }


}
