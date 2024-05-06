package com.atakaya.weatherapp.domain.usecase

import com.atakaya.weatherapp.MainApplication
import com.atakaya.weatherapp.data.remote.api.ApiResult
import com.atakaya.weatherapp.data.remote.models.Icon
import com.atakaya.weatherapp.data.remote.models.request.CurrentWeatherRequest
import com.atakaya.weatherapp.data.remote.models.response.WeatherResponse
import com.atakaya.weatherapp.domain.model.CurrentWeatherForUI
import com.atakaya.weatherapp.domain.repository.WeatherRepositoryImp
import com.atakaya.weatherapp.utils.extensions.asResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepositoryImp: WeatherRepositoryImp,
    private val mainApplication: MainApplication,
) : ParameterizedUseCase<Flow<ApiResult<CurrentWeatherForUI?>>, CurrentWeatherRequest>() {
    override fun exec(params: CurrentWeatherRequest): Flow<ApiResult<CurrentWeatherForUI?>> {
        return weatherRepositoryImp.getCurrentWeather(params.lat, params.long)
            .map(::mappedResponseForUI).asResult()
    }

    private fun mappedResponseForUI(response: WeatherResponse?): CurrentWeatherForUI {
        return CurrentWeatherForUI(
            current = response?.current,
            location = response?.location,
            weatherIconName = getIconName(response?.current?.condition?.code?.toInt().toString())
        )
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
