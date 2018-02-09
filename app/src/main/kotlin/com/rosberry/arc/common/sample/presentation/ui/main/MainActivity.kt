package com.rosberry.arc.common.sample.presentation.ui.main

import android.os.Bundle
import android.view.MenuItem

import com.rosberry.arc.common.presentation.ui.base.BaseActivity
import com.rosberry.arc.common.sample.R
import com.rosberry.arc.common.sample.presentation.App
import com.rosberry.arc.common.sample.presentation.ui.main.list.AwesomeAdapter
import com.rosberry.arc.common.sample.repository.jni.JNIAdapter.stringFromJNI
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

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).diHelper.getUseCaseComponent().inject(this)
        super.onCreate(savedInstanceState)
        viewHolder = MainActivityViewHolder(this)
        presenter.onCreate(this)
        recyclerAwesome.setHasFixedSize(true)
        recyclerAwesome.adapter = awesomeAdapter;

    }

    override fun setCenterText(str: String) {
        textHello.setText(str)
    }

    override fun getViewTag(): String {
        return MainView.TAG
    }


}
