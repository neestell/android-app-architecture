package com.rosberry.arc.common.repository

/**
 * Created by neestell on 15.09.16.
 */
interface Callback<in T> {
    fun onResult(value: T)
}
