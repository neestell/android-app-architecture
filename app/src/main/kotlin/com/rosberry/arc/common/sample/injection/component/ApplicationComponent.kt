package com.rosberry.arc.common.sample.injection.component

import android.content.Context
import com.google.gson.Gson
import com.rosberry.arc.common.repository.persistence.prefs.InternalStorage
import com.rosberry.arc.common.repository.text.TextEncoder
import com.rosberry.arc.common.sample.injection.module.*
import com.rosberry.arc.common.sample.presentation.App
import com.rosberry.arc.common.sample.presentation.navigation.ActivityNavigator
import com.rosberry.arc.common.sample.presentation.navigation.DialogNavigator
import com.rosberry.arc.common.sample.presentation.navigation.FragmentNavigator
import com.rosberry.arc.common.sample.presentation.navigation.ToastNavigator
import com.rosberry.arc.common.sample.repository.persistence.AuthorizationRepository
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by dmitry on 06.06.17.
 */
@Singleton
@Component(modules = arrayOf(
        NetModule::class,
        ApiModule::class,
        RepositoriesModule::class,
        ApplicationModule::class,
        ExternalStorageModule::class
))

interface ApplicationComponent {

    fun inject(app: App)

    fun context(): Context

    fun retrofit(): Retrofit

    fun internalStorage(): InternalStorage

    fun activityNavigator(): ActivityNavigator

    fun fragmentNavigator(): FragmentNavigator

    fun dialogNavigator(): DialogNavigator

    fun toastNavigator(): ToastNavigator

    fun gson(): Gson

    fun textEncoder(): TextEncoder

    fun getAuthorizationRepository(): AuthorizationRepository


}