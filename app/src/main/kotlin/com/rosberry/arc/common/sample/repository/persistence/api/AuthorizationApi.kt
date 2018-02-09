package com.rosberry.arc.common.sample.repository.persistence.api

import com.rosberry.arc.common.sample.repository.persistence.api.response.LoginResponse
import io.reactivex.Observable
import retrofit2.http.POST

interface AuthorizationApi {

    @POST("login")
    fun login(username: String, password: String): Observable<LoginResponse>
}
