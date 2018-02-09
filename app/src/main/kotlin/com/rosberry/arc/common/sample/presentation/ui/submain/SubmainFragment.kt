package com.rosberry.arc.common.sample.presentation.ui.submain

import android.os.Bundle
import android.view.View
import com.rosberry.arc.common.presentation.ui.base.BaseFragment
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.sample.presentation.App

/**
 * Created by sergey on 11/8/17.
 */
class SubmainFragment : BaseFragment<SubmainFragmentViewHolder, SubmainFragmentPresenter>(), SubmainView {


    companion object {
        val TAG = SubmainView.TAG

        fun newInstance(bundle: Bundle?): SubmainFragment {
            return SubmainFragment().apply { arguments = bundle }
        }
    }

    init {

        fragmentModel.layoutId = R.layout.f_submain
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as App).diHelper.getUseCaseComponent().inject(this)
        super.onCreate(savedInstanceState)
        presenter.onCreate(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewHolder = SubmainFragmentViewHolder(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getViewTag(): String {
        return SubmainView.TAG
    }

}