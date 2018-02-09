package com.rosberry.arc.common.presentation.ui.base

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.rosberry.arc.common.R
import com.rosberry.arc.common.presentation.ui.base.model.DialogModel


/**
 * Created by neestell on 06.09.16.
 */
/*Animation, view state and other shit holder*/
abstract class BaseViewHolder : RecyclerView.ViewHolder {
    var toolBar: Toolbar? = null
    private var decorView: View? = null


    public interface OnItemClickListener<T> {
        fun onItemClicked(item: T, position: Int)
    }

    constructor(itemView: View?) : super(itemView)

    constructor(activity: AppCompatActivity) : super(activity.window.decorView) {
        decorView = activity.window.decorView
    }

    fun makeFullscreen() {
        decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    fun hideStatusBar() {
        decorView?.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    fun exists(): Boolean = true

    fun getString(id: Int, vararg args: Any): String = itemView.resources.getString(id, *args)

    fun getColor(color: Int): Int = ContextCompat.getColor(itemView.context, color)

    fun getVectorDrawable(@DrawableRes resId: Int): Drawable? {
        return AppCompatResources.getDrawable(itemView.context, resId)
    }


}
