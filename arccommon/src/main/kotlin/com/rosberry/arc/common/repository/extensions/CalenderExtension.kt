package com.rosberry.arc.common.repository.extensions

import java.util.*

/**
 * Created by dmitry on 05.10.17.
 */
fun Calendar.localToGMT(): Long {

    val date = Calendar.getInstance(TimeZone.getTimeZone("GMT")).timeInMillis

    return date
}