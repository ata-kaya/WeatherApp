package com.atakaya.weatherapp.domain.repository

import com.atakaya.weatherapp.data.remote.models.response.WeatherResponse
import com.atakaya.weatherapp.data.repository.WeatherRepository
import com.atakaya.weatherapp.data.repository.WeatherRepositoryImp
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val weatherRepositoryImp: WeatherRepositoryImp,
) : WeatherRepository {
    override fun getCurrentWeather(lat: String, long: String): Flow<WeatherResponse?> {
        return weatherRepositoryImp.getCurrentWeather(lat, long)
    }

    override fun getAllWeathers(): Flow<List<WeatherResponse?>> {
        return weatherRepositoryImp.getAllWeathers()
    }
}
