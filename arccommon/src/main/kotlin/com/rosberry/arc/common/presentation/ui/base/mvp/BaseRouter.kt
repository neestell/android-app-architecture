package com.rosberry.arc.common.presentation.ui.base.mvp

import android.app.Dialog
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.rosberry.arc.common.R

import com.rosberry.arc.common.presentation.ui.base.BaseActivity
import com.rosberry.arc.common.presentation.ui.base.model.DialogModel
import com.rosberry.arc.common.repository.extensions.removeFragmentByTag
import com.rosberry.arc.common.repository.persistence.internal.ViewDataRepository

/**
 * Created by Evgeniy Nagibin on 22/05/2017.
 */

/*@PerActivity */abstract class BaseRouter
constructor() {

    lateinit var androidAdapter: FrameworkAdapter<ViewDataRepository>



    fun finish(view: BaseView?) {

        when(view){
            is BaseActivity<*, *> -> (view as? BaseActivity<*, *>)?.finish()
            is Dialog -> dismissDialog(view as MaterialDialog)
            is Fragment -> removeFragment(androidAdapter.fragmentManager, view.getViewTag())
        }
    }

    fun  attachAndroidAdapter(androidAdapter: FrameworkAdapter<ViewDataRepository>) {
        this.androidAdapter = androidAdapter
    }

    fun dismissDialog(dialog: MaterialDialog?) {

        dialog?.let {
            try {
                dialog.dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun removeFragment(fm: FragmentManager, tag: String) {
        fm.removeFragmentByTag(tag)
    }


}