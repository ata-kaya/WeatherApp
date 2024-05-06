package com.atakaya.weatherapp.di

import android.content.Context
import com.atakaya.weatherapp.MainApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideMainApplication(@ApplicationContext context: Context): MainApplication {
        return context as MainApplication
    }
}
