package com.rosberry.arc.common.repository.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
import android.widget.ImageView


/**
 * Created by dmitry on 07.06.17.
 */

fun ViewGroup.inflate(layoutId: Int, attachRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachRoot)
}

fun ImageView.loadUrl(pictureUrl: String?, placeholderId: Int = 0, width: Int = 0, height: Int = 0) {
   /* val picasso = GlideApp.with(context)
    val requestCreator: GlideRequest<Drawable>?

    requestCreator = when {
        pictureUrl.isNullOrEmpty() -> picasso.load(R.drawable.empty)
        pictureUrl?.startsWith("http")!! -> picasso.load(pictureUrl)
        else -> picasso.load(File(pictureUrl))
    }
    if (width != 0 && height != 0) {
        requestCreator.override(width, height)
        requestCreator.centerCrop()
    }
    if (placeholderId != 0)
        requestCreator.placeholder(placeholderId)

    requestCreator.dontAnimate()
    requestCreator.into(this)*/
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.showPopAnimation() {
    visibility = VISIBLE
    val scaleAnimation = ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f)
    scaleAnimation.fillAfter = true
    scaleAnimation.duration = 300
    startAnimation(scaleAnimation)
}