package com.rosberry.arc.common.sample.presentation.ui.main

import android.os.Bundle
import android.view.MenuItem

import com.rosberry.arc.common.presentation.ui.base.BaseActivity
import com.rosberry.arc.common.repository.extensions.hide
import com.rosberry.arc.common.repository.extensions.show
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.sample.presentation.App
import com.rosberry.arc.common.sample.presentation.ui.main.list.AwesomeAdapter
import kotlinx.android.synthetic.main.a_main.*

class MainActivity : BaseActivity<MainActivityViewHolder, MainActivityPresenter>(), MainView {

    private val awesomeAdapter = AwesomeAdapter()

    init {
        activityModel.hasActionBar = true
        activityModel.hasBack = true
        activityModel.layoutId = R.layout.a_main
        activityModel.menu = R.menu.m_main
        activityModel.titleFont = R.font.roboto_bold
        activityModel.title = R.string.app_title
    }

    override fun getViewTag(): String {
        return MainView.TAG
    }

    /*--Start user interaction events--*/

    override fun onBackPressed() {
        presenter.clickHardBack()
    }

    override fun onSoftBackClicked() {
        presenter.clickSoftBack()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.op_help -> presenter.clickOptionHelp()
            R.id.op_delete -> presenter.clickOptionDelete()
            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }

    /*--End user interaction events--*/

    /*--Start lifecycle events--*/

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).diHelper.getUseCaseComponent().inject(this)
        super.onCreate(savedInstanceState)
        viewHolder = MainActivityViewHolder(this)
        presenter.onCreate(this)
        recyclerAwesome.setHasFixedSize(true)
        recyclerAwesome.adapter = awesomeAdapter;

    }
    /*--End lifecycle events--*/

    /*--Start change view data events and visibility or animation--*/

    override fun setCenterText(str: String) {
        textHello.setText(str)
    }

    override fun setCenterTextVisible(visible: Boolean) {
        if (visible) textHello.show() else textHello.hide()
    }

    /*--End change view data events and visibility or animation--*/

}
