package com.rosberry.arc.common.repository.persistence.prefs

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.rosberry.arc.common.R
import com.rosberry.arc.common.configuration.BundleConstants
import com.rosberry.arc.common.repository.text.TextEncoder
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by neestell on 06.10.14.
 *
 *
 * Class for store application data
 */
@Singleton
class InternalStorage
@Inject constructor(val prefs: SharedPreferences, private val ctx: Context) {

    fun getAccessToken() = getDecodedString(BundleConstants.UID, "")

    fun saveAccessToken(accessToken: String) = putEncodedString(BundleConstants.UID, accessToken)

    fun hasToken() = !TextUtils.isEmpty(getAccessToken())

    fun getBool(key: String, defValue: Boolean) = prefs.getBoolean(key, defValue)

    fun putBool(key: String, value: Boolean) = prefs.edit().putBoolean(key, value).apply()

    fun getString(key: String, defValue: String) = prefs.getString(key, defValue)

    fun putString(key: String, value: String) = prefs.edit().putString(key, value).apply()

    fun getInt(key: String, defValue: Int) = prefs.getInt(key, defValue)

    fun putInt(key: String, value: Int) = prefs.edit().putInt(key, value).apply()

    fun getDouble(key: String, defValue: Double) = prefs.getFloat(key, defValue.toFloat()).toDouble()

    fun putDouble(key: String, value: Double) = prefs.edit().putFloat(key, value.toFloat()).apply()

    fun getSet(key: String, defValue: Set<String>) = prefs.getStringSet(key, defValue)

    fun putSet(key: String, value: Set<String>) = prefs.edit().putStringSet(key, value).apply()

    fun getLong(key: String, defValue: Long) = prefs.getLong(key, defValue)

    fun putLong(key: String, value: Long) = prefs.edit().putLong(key, value).apply()


    fun putEncodedString(key: String, value: String) = prefs.edit()
            .putString(key, TextEncoder.encrypt(value, ctx.getString(R.string.common_value))).apply()


    fun getDecodedString(key: String, defValue: String): String {
        val result = prefs.getString(key, defValue)
        if (result === defValue)
            return ""
        return TextEncoder.decrypt(result, ctx.getString(R.string.common_value))
    }

    fun remove(key: String) = prefs.edit().remove(key).apply()

    fun reset() {
        prefs.edit()
                .clear()
                .apply()
    }

}