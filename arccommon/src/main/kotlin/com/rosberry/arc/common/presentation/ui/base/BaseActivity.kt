package com.rosberry.arc.common.presentation.ui.base

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.rosberry.arc.common.R
import com.rosberry.arc.common.presentation.ui.base.model.ActivityModel
import com.rosberry.arc.common.presentation.ui.base.mvp.BaseFragmentPresenter
import com.rosberry.arc.common.presentation.ui.base.mvp.BasePresenter
import com.rosberry.arc.common.presentation.ui.base.mvp.BaseView
import com.rosberry.arc.common.repository.extensions.spanText
import com.rosberry.arc.common.repository.log.LogUtil
import com.rosberry.arc.common.repository.text.SpanModel
import java.util.*
import javax.inject.Inject


/**
 * Created by dmitry on 13.02.17.
 */

abstract class BaseActivity<VH : BaseViewHolder, V : BasePresenter<*, *, *>> : AppCompatActivity(), BaseView {

    companion object {

        var inBackground = false

    }

    @Inject protected lateinit var presenter: V


    lateinit var viewHolder: VH

    var isStateSaved = false

    protected val activityModel = ActivityModel()


    open fun onSoftBackClicked() {
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.androidAdapter.restoreInstanceState(savedInstanceState)
        presenter.androidAdapter.attachFragmentManager(supportFragmentManager);

        setContentView(activityModel.layoutId)
        activityModel.setActivity(this)

    }

    override fun onStart() {
        super.onStart()
        if (viewHolder.toolBar == null && activityModel.toolbarId != -1)
            viewHolder.toolBar = findViewById(activityModel.toolbarId)
        setupActionBar(viewHolder.toolBar)
    }

    override fun onResume() {
        super.onResume()
        inBackground = false
        presenter.onResume()
        isStateSaved = false
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        inBackground = true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    public override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.androidAdapter.saveInstanceState(outState)

        isStateSaved = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.androidAdapter.onActivityResult(requestCode, resultCode, data)
        LogUtil.d(this, String.format(Locale.US, "onActivityResult(%d, %d, %s)", requestCode, resultCode,
                data?.toString() ?: "no data"))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            android.R.id.home -> {
                onSoftBackClicked(); return true
            }

            else -> return super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        when (activityModel.menu) {
            -1 -> return super.onCreateOptionsMenu(menu)
            else -> menuInflater.inflate(activityModel.menu, menu)
        }

        return true

    }

    protected fun setupActionBar(toolbar: Toolbar?) {
        toolbar?.let {

            if (activityModel.hasActionBar) {
                setSupportActionBar(toolbar)
                supportActionBar?.let {
                    (supportActionBar as ActionBar).setDisplayShowTitleEnabled(true)
                }

                updateBackButton()

                if (activityModel.title != -1)
                    title = getString(activityModel.title)
            }

        }



    }

    fun updateBackButton() {
        if (supportActionBar == null)
            return

        if (activityModel.hasBack) {
            with(supportActionBar!!) {
                setHomeButtonEnabled(true)
                setDisplayShowHomeEnabled(true)
                setDisplayHomeAsUpEnabled(true)
            }
        } else {
            with(supportActionBar!!) {
                setHomeButtonEnabled(true)
                setDisplayShowHomeEnabled(false)
                setDisplayHomeAsUpEnabled(false)
                setHomeAsUpIndicator(R.color.transparent)
            }
        }
    }

    fun setSpannedTitle(title: String?) {
        actionBar?.let {
            val defaultTitle = if (supportActionBar?.title != null) supportActionBar?.title else ""
            var newTitle = title

            if (newTitle == null) newTitle = defaultTitle as String

            supportActionBar?.title = newTitle.spanText(this, SpanModel.Builder()
                    .typeface(activityModel.titleFont)
                    .color(R.color.white).build())
        }
    }

    fun addChildPresenter(viewTag: String, presenter: BaseFragmentPresenter<*, *, *>) {
        this.presenter.addChildPresenter(viewTag, presenter)
    }

    fun removeChildPresenter(viewTag: String, presenter: BaseFragmentPresenter<*, *, *>) {
        this.presenter.removeChildPresenter(viewTag, presenter)
    }

    override fun getParentView(): BaseView.Host? {
        return if (this is BaseView.Host) this else null
    }

    override fun getViewTag() = javaClass.name

    override fun exists(): Boolean = viewHolder.exists()

    override fun getArguments(): Bundle = intent.extras


}
