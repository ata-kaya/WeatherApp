package com.atakaya.weatherapp.di

import androidx.room.Room
import com.atakaya.weatherapp.MainApplication
import com.atakaya.weatherapp.data.local.database.CityDatabase
import com.atakaya.weatherapp.data.local.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: MainApplication
    ): WeatherDatabase {
        return Room.databaseBuilder(
            application, WeatherDatabase::class.java, "weatherDatabase.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCityDatabase(
        application: MainApplication,
    ): CityDatabase {
        return Room.databaseBuilder(
            application, CityDatabase::class.java, "cityDatabase.db"

        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCityDao(
        cityDatabase: CityDatabase
    ) = cityDatabase.cityDao()

    @Provides
    @Singleton
    fun provideWeatherDao(
        weatherDatabase: WeatherDatabase
    ) = weatherDatabase.weatherDao()
}
