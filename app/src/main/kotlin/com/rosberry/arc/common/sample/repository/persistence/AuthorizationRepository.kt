package com.rosberry.arc.common.sample.repository.persistence

import com.rosberry.arc.common.sample.presentation.model.UserModel
import io.reactivex.Observable

interface AuthorizationRepository {
    fun login(username: String, password: String): Observable<UserModel>
}
