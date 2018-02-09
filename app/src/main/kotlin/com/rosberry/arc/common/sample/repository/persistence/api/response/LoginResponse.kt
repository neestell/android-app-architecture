package com.rosberry.arc.common.sample.repository.persistence.api.response

import com.google.gson.annotations.SerializedName
import com.rosberry.arc.common.repository.persistence.api.response.BaseResponse

/**
 * Created by dmitry on 29.03.17.
 */

class LoginResponse : BaseResponse() {
    @SerializedName("login") var token: String = ""

}
