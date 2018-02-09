package com.rosberry.arc.common.repository.text

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import java.util.*
import java.util.regex.Pattern

/**
 * Created by dmitry on 25.09.14.
 */
object TextUtils {
    val ROBOTO_MEDIUM = "fonts/Roboto-Medium.ttf"

    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    )

    private val typefaceHashMap = HashMap<String, Typeface>()

    fun getTypeface(context: Context, typeface: String): Typeface {
        if (!typefaceHashMap.containsKey(typeface)) {
            val tf = Typeface.createFromAsset(context.assets, typeface)
            typefaceHashMap.put(typeface, tf)
        }
        return typefaceHashMap[typeface] as Typeface
    }

    fun setTypeface(context: Context, typeface: String, v: View?) {
        if (v == null || v !is TextView)
            return
        val tf = getTypeface(context, typeface)
        v.typeface = tf
    }


    fun mayBeJSON(string: String?): Boolean {
        return string != null && ("null" == string
                || string.startsWith("[") && string.endsWith("]")
                || string.startsWith("{") && string.endsWith("}"))
    }

    fun checkEmail(email: CharSequence?): Boolean {
        return email != null && EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    fun isEmpty(str: String): Boolean {
        return android.text.TextUtils.isEmpty(str)
    }

}
