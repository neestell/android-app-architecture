package com.rosberry.arc.common.sample.injection.module

import android.content.Context
import com.rosberry.arc.common.sample.repository.persistence.AuthorizationRepository
import com.rosberry.arc.common.sample.repository.persistence.api.AuthorizationApi
import com.rosberry.arc.common.sample.repository.persistence.api.repository.AuthorizationRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by neestell on 9/7/16.
 *
 *
 * Module for provides repositories
 */
@Module
class RepositoriesModule {

    @Singleton
    @Provides
    fun providesAuthorizationRepository(context: Context, api: AuthorizationApi): AuthorizationRepository =
            AuthorizationRepositoryImpl(context, api)

}
