package com.rosberry.arc.common.sample.repository.persistence.api.repository

import android.content.Context
import com.rosberry.arc.common.repository.persistence.CommonRepository
import com.rosberry.arc.common.sample.presentation.model.UserModel
import com.rosberry.arc.common.sample.repository.persistence.AuthorizationRepository
import com.rosberry.arc.common.sample.repository.persistence.api.AuthorizationApi
import io.reactivex.Observable

class AuthorizationRepositoryImpl constructor(context: Context, service: AuthorizationApi) :
        CommonRepository<AuthorizationApi>(context, service), AuthorizationRepository {

    override fun login(username: String, password: String): Observable<UserModel> =
            createObservable(service.login(username, password).map { UserModel() })
}
