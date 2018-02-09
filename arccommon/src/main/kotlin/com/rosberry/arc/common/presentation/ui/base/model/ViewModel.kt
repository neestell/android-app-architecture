package com.rosberry.arc.common.presentation.ui.base.model

import android.content.Context
import android.content.IntentFilter
import android.os.Handler
import android.os.IBinder
import android.support.v4.content.LocalBroadcastManager
import android.text.InputFilter
import android.util.SparseArray
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.rosberry.arc.common.presentation.ui.base.BaseActivity
import com.rosberry.arc.common.repository.base.BaseBroadcastReceiver
import java.io.Serializable
import java.util.*

/**
 * Created by dmitry on 25.09.14.
 */
abstract class ViewModel : Serializable {

     var layoutId: Int = 0

    @Transient val handler = Handler()
    @Transient private var viewContext: ViewContext? = null
    @Transient private val inputFilters = SparseArray<Array<InputFilter>>()
    @Transient private val receivers = HashMap<BaseBroadcastReceiver, IntentFilter>()

    var context: Context
        get() = viewContext!!.getContext()
        set(context) {
            clearResources()
            viewContext = ViewContext(context)
            setup()
        }

    private fun setup() {
        setInputFilters(inputFilters)
    }

    fun setActivity(activity: BaseActivity<*, *>) {
        clearResources()
        viewContext = ViewContext(activity)
        setup()
    }

    fun setView(view: View) {
        clearResources()
        viewContext = ViewContext(view)
        setup()
    }

    private fun clearResources() {
        viewContext = null
    }


    fun addInputFilter(inputFilters: Array<InputFilter>, vararg ids: Int) {
        for (id in ids) {
            this.inputFilters.put(id, inputFilters)
        }
    }

    private fun setInputFilters(filters: SparseArray<Array<InputFilter>>) {
        if (viewContext!!.isView) {
            for (i in 0..filters.size() - 1) {
                val key = filters.keyAt(i)
                val value = filters.get(key)
                (viewContext!!.id(key) as EditText).filters = value

            }

        }
    }

    fun registerReceivers() {
        for ((key, value) in receivers) {
            if (key.isGeneral) {
                context.registerReceiver(key, value)
            } else
                LocalBroadcastManager.getInstance(context.applicationContext).registerReceiver(key,
                        value)
        }
    }

    fun unregisterReceivers() {
        for ((key) in receivers) {
            if (key.isGeneral) {
                context.unregisterReceiver(key)
            } else
                LocalBroadcastManager.getInstance(context).unregisterReceiver(key)
        }
    }

    fun hideKeyboard(vararg view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        for (v in view) {
            imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
            imm.hideSoftInputFromInputMethod(v.windowToken, 0)

        }
    }

    fun hideKeyboard(binder: IBinder) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_IMPLICIT_ONLY)
        imm.hideSoftInputFromInputMethod(binder, InputMethodManager.HIDE_IMPLICIT_ONLY)

    }

    fun hideKeyboardFromWindow(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showKeyboard(searchEditView: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(searchEditView, InputMethodManager.SHOW_FORCED)
    }

    private inner class ViewContext {

        internal var activity: BaseActivity<*, *>? = null
        internal var view: View? = null
        internal var context: Context? = null

        internal constructor(activity: BaseActivity<*, *>) {
            this.activity = activity
        }

        internal constructor(view: View) {
            this.view = view
        }

        internal constructor(context: Context) {
            this.context = context
        }

        val isView: Boolean
            get() = context == null

        fun id(id: Int): View? {
            if (isView)
                if (activity != null)
                    return activity!!.findViewById(id)
                else
                    return view?.findViewById(id)

            return null
        }

        fun getContext(): Context {
            return if (isView) if (activity == null) (view?.context as Context)
            else (activity as Context)
            else context as Context
        }
    }

}
