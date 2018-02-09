package com.rosberry.arc.common.presentation.ui.base.mvp

import android.content.Context
import android.os.Bundle
import android.support.annotation.ArrayRes
import android.support.annotation.NonNull
import android.support.annotation.StringRes
import com.rosberry.arc.common.repository.persistence.internal.ViewDataRepository

/**
 * Created by dmitry on 30.03.17.
 */

abstract class ViewDataStorage(@Transient val context: Context, @Transient val storage: ViewDataRepository) {

    @NonNull fun getString(@StringRes str: Int, vararg args: Any): String {
        return context.getString(str, *args)
    }

    fun getArray(@ArrayRes array: Int): Array<String> {
        return context.resources.getStringArray(array)
    }

    open fun onCreate(bundle: Bundle) {

    }
}
