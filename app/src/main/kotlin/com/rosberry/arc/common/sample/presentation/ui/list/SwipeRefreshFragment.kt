package com.rosberry.arc.common.sample.presentation.ui.list

import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.rosberry.arc.common.presentation.ui.base.BaseFragment
import com.rosberry.arc.common.repository.extensions.hide
import com.rosberry.arc.common.repository.extensions.show
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.sample.presentation.App
import com.rosberry.arc.common.sample.presentation.model.PlaceholderModel
import com.rosberry.arc.common.sample.presentation.ui.main.list.AwesomeAdapter
import com.rosberry.arc.common.sample.presentation.ui.main.list.AwesomeModel
import kotlinx.android.synthetic.main.f_swipe_refresh.*
import kotlinx.android.synthetic.main.l_loading.*
import kotlinx.android.synthetic.main.l_placeholder.*

/**
 * Created by sergey on 11/8/17.
 */
class SwipeRefreshFragment : BaseFragment<SwipeRefreshFragmentViewHolder, SwipeRefreshFragmentPresenter>(),
        SwipeRefreshView, AwesomeAdapter.OnItemClickListener {

    companion object {
        val TAG = SwipeRefreshView.TAG

        fun newInstance(bundle: Bundle?): SwipeRefreshFragment {
            return SwipeRefreshFragment().apply { arguments = bundle }
        }
    }

    private val awesomeAdapter = AwesomeAdapter()


    init {
        fragmentModel.layoutId = R.layout.f_swipe_refresh
    }

    override fun getViewTag(): String {
        return SwipeRefreshView.TAG
    }

    override fun getRetryButtonObservable() = RxView.clicks(buttonRetry)

    override fun onRefresh() {
        presenter.refreshAwesome()
    }

    override fun onAwesomeClicked(item: AwesomeModel, sharedView: View, position: Int) {
        presenter.clickAwesome(item)
    }

    override fun onAwesomeLongClicked(item: AwesomeModel, sharedView: View, position: Int) {
        presenter.clickAwesome(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity?.application as App).diHelper.getUseCaseComponent().inject(this)
        super.onCreate(savedInstanceState)
        presenter.onCreate(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewHolder = SwipeRefreshFragmentViewHolder(view)
        super.onViewCreated(view, savedInstanceState)

        recyclerAwesome.setHasFixedSize(true)
        recyclerAwesome.adapter = awesomeAdapter
        awesomeAdapter.itemClickListener = this
    }

    override fun setProgressVisible(b: Boolean) {
        if (b) layoutProgress?.show() else layoutProgress?.hide()
    }

    override fun setPlaceholderVisible(b: Boolean) {
        if (b) layoutPlaceholder?.show() else layoutPlaceholder?.hide()
    }

    override fun setRefreshEnabled(b: Boolean) {
        swipeRefreshAwesome?.isEnabled = b
    }

    override fun setRefreshIndicatorVisible(b: Boolean) {
        swipeRefreshAwesome?.isRefreshing = b
    }

    override fun setPlaceholder(placeholderModel: PlaceholderModel) {
        imagePlaceholderIcon?.setImageResource(placeholderModel.icon)
        textPlaceholderSubtitle?.setText(placeholderModel.subtitle)
    }

    override fun setAwesome(awesome: ArrayList<AwesomeModel>) {
        awesomeAdapter.updateItems(awesome)
    }

}