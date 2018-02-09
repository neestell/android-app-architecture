package com.rosberry.arc.common.sample.usecase.auth

import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage
import com.rosberry.arc.common.sample.presentation.model.UserModel
import com.rosberry.arc.common.sample.repository.persistence.AuthorizationRepository
import com.rosberry.arc.common.usecase.CommonUseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Evgeniy Nagibin on 08/11/2017.
 */
class AuthUseCase @Inject constructor(storage: InternalStorage,
                                      repository: AuthorizationRepository) :
        CommonUseCase<AuthorizationRepository>(storage, repository) {

    fun login(username: String, password: String): Observable<UserModel> {
        return defaultObservable(repository.login(username, password))
    }
}