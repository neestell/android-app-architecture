package com.rosberry.arc.common.usecase

import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by neestell on 9/7/16.
 */
abstract class CommonUseCase<R>(protected val storage: InternalStorage, protected var repository: R) {

    protected fun <T> defaultObservable(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
    }

    protected fun <T> defaultFlowable(flowable: Flowable<T>): Flowable<T> {
        return flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
    }

    protected fun <T> defaultMaybe(flowable: Maybe<T>): Maybe<T> {
        return flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
    }

    protected fun <T> defaultAsyncObservable(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
    }

}
