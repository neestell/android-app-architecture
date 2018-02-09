package com.rosberry.arc.common.presentation.ui.base.mvp

import com.rosberry.arc.common.presentation.ui.base.BaseActivity
import com.rosberry.arc.common.repository.Callback
import com.rosberry.arc.common.repository.log.LogUtil
import com.rosberry.arc.common.repository.persistence.internal.ViewDataRepository
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import java.util.*

/**
 * Created by neestell on 9/28/16.
 */
abstract class BasePresenter<VI:BaseView,  D: ViewDataRepository, RO: BaseRouter>(val viewData: D, val router: RO)  {
    private val childPresenters = HashMap<String, BasePresenter<*, *, *>>()
    val androidAdapter: FrameworkAdapter<D> = FrameworkAdapter(viewData)
    var v: VI? = null

    var subscriptions = CompositeDisposable()
        protected set

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

        if (!subscriptions.isDisposed)
            subscriptions.dispose()
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
