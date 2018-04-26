package com.rosberry.arc.common.sample.presentation.model

import android.content.Context
import android.support.annotation.DrawableRes
import com.rosberry.arc.common.repository.exception.NetworkConnectionException
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.sample.repository.model.ErrorModel

import retrofit2.HttpException

class PlaceholderModel(errorModel: ErrorModel?) {
    @DrawableRes var  icon: Int = -1
    var title: Int = -1
    var subtitle: Int = -1

    init {
        title = R.string.something_went_wrong

        if (errorModel?.throwable is HttpException){
            icon = android.R.drawable.ic_delete
            subtitle = R.string.our_engineers_fix_it
        }else if (errorModel?.throwable is NetworkConnectionException){
            icon = android.R.drawable.ic_dialog_alert
            subtitle = R.string.check_network
        }else{
            icon = android.R.drawable.ic_delete
            subtitle = R.string.unknown_error
        }
    }

    fun toMessage(context: Context): String {
        return String.format("%s\n%s", context.getString(title), context.getString(subtitle))
    }
}