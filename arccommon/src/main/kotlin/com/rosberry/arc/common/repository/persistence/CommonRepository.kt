package com.rosberry.arc.common.repository.persistence

import android.content.Context
import android.support.annotation.ArrayRes
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import io.reactivex.Observable


abstract class CommonRepository< out S>(val context: Context, val service: S) {

    fun getString(stringRes: Int): String = context.getString(stringRes)

    fun getString(@StringRes str: Int, vararg args: Any): String = context.getString(str, *args)

    fun getArray(@ArrayRes array: Int): Array<String> = context.resources.getStringArray(array)

    fun getColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }

    open fun <T> createObservable(observable: Observable<T>): Observable<T> {
        return Observable.defer<T> {
            Observable.unsafeCreate<T> { subscriber ->
                observable.subscribe({ subscriber.onNext(it) },
                        { subscriber.onError(it) },
                        { subscriber.onComplete() })
            }
        }
    }
}
