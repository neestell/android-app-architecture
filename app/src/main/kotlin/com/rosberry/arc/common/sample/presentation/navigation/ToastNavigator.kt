package com.rosberry.arc.common.sample.presentation.navigation

import android.content.Context
import android.widget.Toast
import com.rosberry.arc.common.presentation.ui.base.model.DialogModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by dmitry on 07.02.2018.
 */
@Singleton
class ToastNavigator @Inject constructor(val context: Context){

    fun show(model: DialogModel){

        val msg = if (model.content.isEmpty())
            context.resources.getString(model.contentId)
        else
            model.content

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun cancelToast(toast: Toast?) {

        toast?.let { toast.cancel() }
    }
}