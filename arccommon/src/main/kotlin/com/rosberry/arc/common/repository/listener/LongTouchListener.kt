package com.rosberry.arc.common.repository.listener

import android.os.Handler
import android.view.MotionEvent

/**
 * Created by dmitry on 10.01.2018.
 */

class LongTouchListener {
    private var clickType = CLICK_SINGLE

    private var handler: Handler? = null
    private var listener: Listener? = null
    private var longClickStartEvent: MotionEvent? = null

    private var time: Long? = null
    private var timeRepeat: Long = 1
    private var clickThreshold = 15
    private val action = object : Runnable {

        override fun run() {
            if (longClickStartEvent != null) listener!!.onLongClick(longClickStartEvent)
            clickType = CLICK_LONG
            handler!!.postDelayed(this, timeRepeat)

        }
    }

    interface Listener {

        fun onLongClick(event: MotionEvent?)

        fun onClick() {

        }

        fun onRelease(){

        }
    }

    constructor(listener: Listener, time: Long?, clickThreshold: Int) {
        this.listener = listener
        this.time = time
        this.clickThreshold = clickThreshold
    }

    constructor(listener: Listener, time: Long?, timeRepeat: Long) {
        this.listener = listener
        this.time = time
        this.timeRepeat = timeRepeat
    }

    fun onTouchEvent(event: MotionEvent): Boolean {
        if (handler == null) {
            handler = Handler()
            handler!!.postDelayed(action, time!!)
        }
        if (event.pointerCount > 1){
            releaseLongPress()

            return false
        }

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                clickType = CLICK_SINGLE
                this.longClickStartEvent = MotionEvent.obtain(event)

                return true
            }

            MotionEvent.ACTION_POINTER_DOWN ->{
                releaseLongPress()

                return false
            }
            MotionEvent.ACTION_CANCEL -> {
                releaseLongPress()

                return false
            }

            MotionEvent.ACTION_MOVE -> {
                if (this.longClickStartEvent != null){
                   if (Math.abs(this.longClickStartEvent!!.x - event.x) > clickThreshold
                           || Math.abs(this.longClickStartEvent!!.y - event.y) > clickThreshold){
                       releaseLongPress()

                   }
                }
                return false

            }

            MotionEvent.ACTION_UP -> {
                if (clickType == CLICK_SINGLE) {
                    listener!!.onClick()
                }
                releaseLongPress()

                return true
            }
        }
        return false
    }

    private fun releaseLongPress() {
        handler!!.removeCallbacks(action)
        handler = null
        listener?.onRelease()
    }

    companion object {

        private val CLICK_SINGLE: Short = 1
        private val CLICK_LONG: Short = 2
    }

}
