package com.atakaya.weatherapp.data.repository

import com.atakaya.weatherapp.data.local.database.dao.CityDao
import com.atakaya.weatherapp.data.local.database.dao.WeatherDao
import com.atakaya.weatherapp.data.local.models.WeatherDaoModel
import com.atakaya.weatherapp.data.remote.api.ApiService
import com.atakaya.weatherapp.data.remote.models.response.WeatherResponse
import com.atakaya.weatherapp.utils.Helper
import com.atakaya.weatherapp.utils.extensions.getCityModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val cityDao: CityDao,
    private val weatherDao: WeatherDao
) : WeatherRepository {
    override fun getCurrentWeather(lat: String, long: String): Flow<WeatherResponse?> {
        return flow {
            emit(getLocalWeatherByLocationCoordinates(lat, long))
            emit(getAndFetchRemoteWeatherByLocationCoordinates(lat, long))
        }

    }

    private suspend fun getAndFetchRemoteWeatherByLocationCoordinates(
        lat: String, long: String
    ): WeatherResponse? {
        val remoteWeather = apiService.getCurrentWeather("$lat,$long")
        cityDao.addCity(
            remoteWeather?.location.getCityModel()
        )
        weatherDao.insertNewCity(
            WeatherDaoModel(
                cityId = Helper.getCityId(
                    remoteWeather?.location?.lat, remoteWeather?.location?.lon
                ).toInt(), weather = remoteWeather
            )
        )
        return remoteWeather
    }

    private fun getLocalWeatherByLocationCoordinates(
        lat: String, long: String
    ): WeatherResponse? {
        cityDao.getCity(Helper.getCityId(lat = lat.toDouble(), lon = long.toDouble()).toInt())
            ?.let { city ->
                weatherDao.getWeatherById(cityId = city.cityId)?.let { weather ->
                    if (city.cityName.split("/")[0] == weather.weather?.location?.name) {
                        return weather.weather
                    }
                }
            }
        return null
    }

    override fun getAllWeathers(): Flow<List<WeatherResponse?>> = flow {
        cityDao.getAllCities()?.let { cities ->
            val data = arrayListOf<WeatherResponse?>()
            cities.forEach { city ->
                val lat = Helper.getLat(city.cityId).toString()
                val lon = Helper.getLon(city.cityId).toString()
                data.add(getLocalWeatherByLocationCoordinates(lat, lon))
            }
            emit(data)
            data.clear()
            cities.forEach { city ->
                val lat = Helper.getLat(city.cityId).toString()
                val lon = Helper.getLon(city.cityId).toString()
                data.add(getAndFetchRemoteWeatherByLocationCoordinates(lat, lon))
            }
            emit(data)
        }
    }
}
