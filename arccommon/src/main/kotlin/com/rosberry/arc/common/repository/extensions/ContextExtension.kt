package com.rosberry.arc.common.repository.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat

@SuppressLint("MissingPermission")
fun Context.hasNetwork(): Boolean {

    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return connectivityManager.activeNetworkInfo?.isConnected == true

}


fun Context.getDrawableCompat(@DrawableRes resId: Int): Drawable? {
    return ContextCompat.getDrawable(this, resId)
}