package com.rosberry.arc.common.sample.presentation.navigation

import android.content.Context
import android.support.v4.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.rosberry.arc.common.R
import com.rosberry.arc.common.presentation.ui.base.model.DialogModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by neestell on 06.09.16.
 */
@Singleton
class DialogNavigator @Inject constructor() {

    fun showProgressDialog(context: Context, dialogModel: DialogModel): MaterialDialog {

        val builder = MaterialDialog.Builder(context)
        if (dialogModel.title != 0) builder.title(context.getString(dialogModel.title))

        builder
                .content(dialogModel.content)
                .progress(true, dialogModel.max)
                .cancelable(dialogModel.isCancelable)
                .titleColor(ContextCompat.getColor(context, R.color.black))
                .negativeColor(ContextCompat.getColor(context, R.color.colorAccent))
                .onNegative({ _, _ ->
                    dialogModel.negativeListener?.onResult(false)
                })
                .build()


        return builder.show()
    }

    fun showAlertDialog(context: Context, dialogModel: DialogModel): MaterialDialog {
        val buttonColor = ContextCompat.getColor(context, R.color.primary_dark_material_dark)
        val dialog = MaterialDialog.Builder(context)
                .title(if (dialogModel.title == 0) "" else context.getString(dialogModel.title))
                .titleColor(ContextCompat.getColor(context, R.color.black))
                .content(if (dialogModel.contentId == 0) dialogModel.content else context.getString(dialogModel.contentId))
                .positiveText(dialogModel.positive)
                .negativeText(dialogModel.negative)
                .cancelable(dialogModel.isCancelable)
                .neutralText(dialogModel.neutral)
                .positiveColor(buttonColor)
                .negativeColor(buttonColor)
                .neutralColor(buttonColor)
                .onPositive({ _, _ ->
                    dialogModel.positiveListener?.onResult(true)
                })
                .onNegative({ _, _ ->
                    dialogModel.negativeListener?.onResult(false)
                })
                .onNeutral({ _, _ ->
                    dialogModel.neutralListener?.onResult(true)
                })
                .build()
        dialog.show()
        return dialog
    }

}