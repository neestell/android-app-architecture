package com.rosberry.arc.common.presentation.ui.base.mvp

import com.rosberry.arc.common.repository.log.LogUtil
import com.rosberry.arc.common.repository.persistence.internal.ViewDataRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.*
import kotlin.reflect.KFunction0

/**
 * Created by neestell on 9/28/16.
 */
abstract class BasePresenter<VI:BaseView,  D: ViewDataRepository, RO: BaseRouter>(val viewData: D, val router: RO)  {
    private val childPresenters = HashMap<String, BasePresenter<*, *, *>>()
    val androidAdapter: FrameworkAdapter<D> = FrameworkAdapter(viewData)
    var v: VI? = null

    var subsDisposable = CompositeDisposable()
        protected set
    var subsDisposableMap = HashMap<String, Disposable>()

    fun getFrameworkAdapter() = androidAdapter

    open fun onCreate(view: VI){
        this.v = view
        router.attachAndroidAdapter(androidAdapter);
    }

    open fun onResume() {

    }

    open fun onPause() {

    }

    fun onDestroy() {
        v = null
        childPresenters.clear()

        if (!subsDisposable.isDisposed)
            subsDisposable.dispose()

    }

    fun getView() = v

    fun hasView(): Boolean {
        return v != null && v!!.exists()
    }

    fun addChildPresenter(viewTag: String, presenter: BaseFragmentPresenter<*, *, *>) {
        childPresenters.put(viewTag, presenter)
    }

    fun removeChildPresenter(viewTag: String, presenter: BaseFragmentPresenter<*, *, *>) {
        childPresenters.remove(viewTag)
    }

    fun findChildPresenter(tag: String): BasePresenter<*, *, *>? {
        if (this !is BasePresenter.Host) {
            LogUtil.i(javaClass, "Your Presenter must implement IHost interface to store child presenters")

            return null
        }
        return childPresenters.get(tag)
    }

    fun countChildPresenters(): Int {
        if (this !is BasePresenter.Host) {
            LogUtil.i(javaClass, "Your Presenter must implement IHost interface to store child presenters")
            return -1
        }

        return childPresenters.size
    }

    fun unsubscribeWidget(function: KFunction0<Unit>) {

        val disposable = subsDisposableMap.get(function.name)
        if (disposable != null) {
            subsDisposable.remove(disposable)
            subsDisposableMap.remove(function.name)
        }
    }

    fun subscribeWidget(o: Observable<Any>?, function: KFunction0<Unit>, autoUnsubscribe: Boolean = false) {

        if (o != null && !subsDisposableMap.containsKey(function.name)) {
            val disposable = o.subscribe({
                if (autoUnsubscribe) unsubscribeWidget(function)
                function.invoke()
            })
            if (subsDisposable.add(disposable)) subsDisposableMap.put(function.name, disposable)
        }
    }


    interface Host {

        fun findChildPresenter(tag: String): BasePresenter<*, *, *>?

        fun countChildPresenters(): Int
    }

    companion object {
        fun getView(): BaseView {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }


}
