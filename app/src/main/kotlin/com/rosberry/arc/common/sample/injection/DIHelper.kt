package com.rosberry.arc.common.sample.injection

import com.rosberry.arc.common.sample.injection.component.ApplicationComponent
import com.rosberry.arc.common.sample.injection.component.DaggerApplicationComponent
import com.rosberry.arc.common.sample.injection.component.DaggerUseCaseComponent
import com.rosberry.arc.common.sample.injection.component.UseCaseComponent
import com.rosberry.arc.common.sample.injection.module.ApplicationModule
import com.rosberry.arc.common.sample.injection.module.ExternalStorageModule
import com.rosberry.arc.common.sample.injection.module.NetModule
import com.rosberry.arc.common.sample.injection.module.RepositoriesModule
import com.rosberry.arc.common.sample.presentation.App

/**
 * Created by dmitry on 25.12.2017.
 */
class DIHelper {
    private lateinit var appComponent: ApplicationComponent
    private lateinit var useCaseComponent: UseCaseComponent

    fun getUseCaseComponent(): UseCaseComponent {
        if (!::useCaseComponent.isInitialized){
            useCaseComponent = DaggerUseCaseComponent.builder()
                    .applicationComponent(appComponent)
                    .build()
        }
        return useCaseComponent
    }

    fun initApplicationComponent(app: App) {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(app))
                .netModule(NetModule())
                .repositoriesModule(RepositoriesModule())
                .externalStorageModule(ExternalStorageModule(app))
                .build()
        appComponent.inject(app)
    }
}