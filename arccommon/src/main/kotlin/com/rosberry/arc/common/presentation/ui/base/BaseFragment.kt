package com.rosberry.arc.common.presentation.ui.base

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.ActionBar
import android.view.*
import com.rosberry.arc.common.R

import com.rosberry.arc.common.presentation.ui.base.model.DialogFragmentModel
import com.rosberry.arc.common.presentation.ui.base.model.DialogModel
import com.rosberry.arc.common.presentation.ui.base.mvp.BaseFragmentPresenter
import com.rosberry.arc.common.presentation.ui.base.mvp.BaseView
import com.rosberry.arc.common.repository.log.LogUtil
import javax.inject.Inject


/**
 * Created by neestell on 1/1/16.
 */

abstract class BaseFragment<VH : BaseViewHolder, P : BaseFragmentPresenter<*, *, *>> : DialogFragment(), BaseView {

    protected lateinit var viewHolder: VH
    protected var stateSaved: Boolean = false
    protected var fragmentModel = DialogFragmentModel()

    @Inject lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.androidAdapter.saveInstanceState(savedInstanceState)
        setStyle(fragmentModel.style, R.style.AppDialogNoTitle)
        isCancelable = fragmentModel.isCancelable
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
        stateSaved = false

    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStart() {
        super.onStart()
        LogUtil.d(javaClass, "onStart. fragment: " + this + ", presenter: " + presenter)
        fragmentModel.registerReceivers()
    }

    override fun onStop() {
        super.onStop()
        fragmentModel.unregisterReceivers()
        LogUtil.d(javaClass, "onStop. fragment: " + this + ", presenter: " + presenter)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.androidAdapter.saveInstanceState(outState)
        stateSaved = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(fragmentModel.layoutId, container, false)
        retainInstance = fragmentModel.retainInstance
        setHasOptionsMenu(fragmentModel.retainInstance && fragmentModel.hasOptionMenu)
        fragmentModel.setView(view)
        if (!fragmentModel.isDialog) {
            retainInstance = fragmentModel.retainInstance
            setHasOptionsMenu(fragmentModel.retainInstance && fragmentModel.hasOptionMenu)
        } else {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            dialog.setCanceledOnTouchOutside(fragmentModel.isCancelableOutside)
            dialog.window!!.setLayout(fragmentModel.width, fragmentModel.height)
            dialog.window!!.attributes.verticalMargin = fragmentModel.margin.toFloat()
            dialog.window!!.attributes.horizontalMargin = fragmentModel.margin.toFloat()
            dialog.window!!.attributes.gravity = Gravity.CENTER
            dialog.window!!.attributes.flags = dialog.window!!.attributes.flags and WindowManager.LayoutParams.FLAG_DIM_BEHIND.inv()

            if (fragmentModel.animationId != 0)
                dialog.window!!.setWindowAnimations(fragmentModel.animationId)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setTitle()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.androidAdapter.onActivityResult(requestCode, resultCode, data!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (fragmentModel.hasOptionMenu && fragmentModel.menu != -1)
            inflater.inflate(fragmentModel.menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDestroyed()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun exists(): Boolean {
        return viewHolder.exists()
    }

    private fun setTitle() {
        if (activity != null && fragmentModel.hasToolbar && fragmentModel.titleId != -1)
            (activity as BaseActivity<*, *>).setSpannedTitle(getString(fragmentModel.titleId))
    }

    val isValid: Boolean
        get() = !stateSaved


    val supportActionBar: ActionBar
        get() = (activity as BaseActivity<*, *>).supportActionBar as ActionBar


    override fun getIntent(): Intent {
        return activity?.intent?: Intent("empty")
    }

    fun addChildPresenter(viewTag: String, presenter: BaseFragmentPresenter<*, *, *>) {
        this.presenter.addChildPresenter(viewTag, presenter)
    }

    fun removeChildPresenter(viewTag: String, presenter: BaseFragmentPresenter<*, *, *>) {
        this.presenter.removeChildPresenter(viewTag, presenter)
    }

    abstract override fun getViewTag(): String

    override fun getParentView(): BaseView.Host? {
        val baseView = if (parentFragment == null) activity as BaseView else parentFragment as BaseView
        return if (baseView is BaseView.Host) baseView else null
    }

}
