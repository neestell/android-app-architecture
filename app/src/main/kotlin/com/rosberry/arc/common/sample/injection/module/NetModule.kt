package com.rosberry.arc.common.sample.injection.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rosberry.arc.common.R
import com.rosberry.arc.common.configuration.ApiConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by neestell on 9/7/16.
 *
 *
 * Module for provide net classes instances
 */
@Module
class NetModule {

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
                .create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        val httpClient = OkHttpClient.Builder()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        httpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS)
        httpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
        httpClient.authenticator { _, response ->
            if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // send unauth request
            }
            null
        }


        return httpClient.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(context: Context, httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://www.yandex.ru")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .client(httpClient)
                .build()
    }

    @Provides
    @Singleton
    @Named("Authorized")
    fun providesAuthRetrofit(context: Context, httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://www.yandex.ru")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build()
    }


}
