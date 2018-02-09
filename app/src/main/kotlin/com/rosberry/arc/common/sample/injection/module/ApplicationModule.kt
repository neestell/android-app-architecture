package com.rosberry.arc.common.sample.injection.module

import android.content.Context
import android.content.SharedPreferences
import com.rosberry.arc.common.sample.presentation.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by neestell on 7/2/17.
 *
 *
 * Module for provide singleton instances
 */
@Module
class ApplicationModule(val app: App) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = this.app

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(TAG_STORAGE, Context.MODE_PRIVATE)

    companion object {
        /*private*/ val TAG_STORAGE = "app"
    }

}
