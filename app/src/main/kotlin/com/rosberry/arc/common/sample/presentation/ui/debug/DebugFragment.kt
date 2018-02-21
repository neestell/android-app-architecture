package com.rosberry.arc.common.sample.presentation.ui.debug

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import com.rosberry.arc.common.presentation.ui.base.BaseFragment
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.sample.presentation.App
import kotlinx.android.synthetic.main.f_debug.*

/**
 * Created by sergey on 11/8/17.
 */
class DebugFragment : BaseFragment<DebugFragmentViewHolder, DebugFragmentPresenter>(), DebugView {

    companion object {
        val TAG = DebugView.TAG

        fun newInstance(): DebugFragment {
            return DebugFragment()
        }
    }

    init {
        fragmentModel.layoutId = R.layout.f_debug
        fragmentModel.size(Resources.getSystem().displayMetrics.widthPixels, Resources.getSystem().displayMetrics.heightPixels)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as App).diHelper.getUseCaseComponent().inject(this)
        super.onCreate(savedInstanceState)
        presenter.onCreate(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewHolder = DebugFragmentViewHolder(view)
        super.onViewCreated(view, savedInstanceState)
        debugContainer.layoutParams.width = fragmentModel.width
        debugContainer.layoutParams.height = fragmentModel.height

    }

    override fun getViewTag(): String {
        return DebugView.TAG
    }

}