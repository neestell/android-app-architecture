package com.rosberry.arc.common.repository.persistence.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by dmitry on 29.03.17.
 */

open class BaseResponse {
    @SerializedName("result") var success: Boolean = false
    @SerializedName("error") var errorMessage: String = ""
}
