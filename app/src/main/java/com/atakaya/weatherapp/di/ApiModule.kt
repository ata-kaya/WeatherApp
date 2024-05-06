package com.atakaya.weatherapp.di

import com.atakaya.weatherapp.MainApplication
import com.atakaya.weatherapp.R
import com.atakaya.weatherapp.data.remote.api.ApiService
import com.atakaya.weatherapp.data.remote.api.okhttp.OkHTTPClientImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        application: MainApplication,
        okHttpClient: OkHTTPClientImpl,
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(application.getString(R.string.base_url))
            .client(okHttpClient.getUnsafeOkHTTPClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
