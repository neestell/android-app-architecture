package com.rosberry.arc.common.sample.injection.module


import com.rosberry.arc.common.sample.repository.persistence.api.AuthorizationApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by neestell on 9/7/16.
 */
@Module
class ApiModule {

    @Singleton
    @Provides
    fun providesAuthorizationApi(retrofit: Retrofit): AuthorizationApi = retrofit.create(AuthorizationApi::class.java)

}
