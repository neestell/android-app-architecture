package com.rosberry.arc.common.repository.persistence.api.repository


import android.content.Context
import com.rosberry.arc.common.R
import com.rosberry.arc.common.repository.exception.NetworkConnectionException
import com.rosberry.arc.common.repository.persistence.CommonRepository
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by dmitry on 07.08.17.
 */

abstract class ApiRepository<S>(context: Context, service: S) : CommonRepository<S>(context, service) {

    companion object {

        val MULTIPART_FORM_DATA = "multipart/form-data"
    }


    fun parseError(throwable: Throwable): Throwable {
        throwable.printStackTrace()
        if (throwable is HttpException) {
            try {
                return NetworkConnectionException(context.getString(R.string.network_error), throwable)
            } catch (t: Throwable) {
                return t
            }

        } else if (throwable is SocketTimeoutException ||
                throwable is SocketException ||
                throwable is UnknownHostException) {
            try {
                return NetworkConnectionException(context.getString(R.string.no_internet_connection))
            } catch (t: Throwable) {
                return t
            }

        }

        return throwable
    }

    fun parseCustomError(throwable: Throwable): Throwable {
        val errorBody = (throwable as HttpException).response().errorBody()

        try {
            val msg = errorBody!!.string()
            return NetworkConnectionException(msg, throwable)
        } catch (e: Exception) {
            e.printStackTrace()
            return NetworkConnectionException("")
        }

    }



}
