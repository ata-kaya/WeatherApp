package com.atakaya.weatherapp.domain.usecase

import com.atakaya.weatherapp.MainApplication
import com.atakaya.weatherapp.data.remote.api.ApiResult
import com.atakaya.weatherapp.data.remote.models.Icon
import com.atakaya.weatherapp.data.remote.models.response.WeatherResponse
import com.atakaya.weatherapp.domain.model.CurrentWeatherForUI
import com.atakaya.weatherapp.domain.repository.WeatherRepositoryImp
import com.atakaya.weatherapp.utils.extensions.asResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllWeathersUseCase @Inject constructor(
    private val weatherRepositoryImp: WeatherRepositoryImp,
    private val mainApplication: MainApplication,
) : UseCase<Flow<ApiResult<List<CurrentWeatherForUI?>>>>() {
    override fun execute(): Flow<ApiResult<List<CurrentWeatherForUI?>>> {
        return weatherRepositoryImp.getAllWeathers().map(::mappedResponseForUI).asResult()
    }

    private fun mappedResponseForUI(response: List<WeatherResponse?>): List<CurrentWeatherForUI> {
        return response.map {
            CurrentWeatherForUI(
                current = it?.current,
                location = it?.location,
                weatherIconName = getIconName(it?.current?.condition?.code?.toInt().toString())
            )
        }
    }

    private fun getIconName(iconCode: String?): String {
        iconCode?.let {
            val icons = getIconsSource()
            val icon = icons.find { icon -> icon.code == it }
            return "day_${icon?.icon}"
        }
        return ""
    }

    private fun getIconsSource(): ArrayList<Icon> {
        val fileInString: String =
            mainApplication.assets.open("icons.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<ArrayList<Icon>>() {}.type
        return Gson().fromJson(fileInString, type)
    }
}
