package com.rosberry.arc.common.configuration

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by neestell on 17.07.16.
 */

@Singleton
class LocalConstants @Inject constructor(context: Context) {
    private val mainDir: String = context.getExternalFilesDir(null).path

    companion object {
        val MEDIA_DIR = "media"

        val GOOGLE_PLAY_BASE_URL = "http://play.google.com/store/apps/details?id=%s"
        val GOOGLE_PLAY_BASE_APP = "market://details?id=%s"

    }

}
