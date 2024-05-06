package com.atakaya.weatherapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atakaya.weatherapp.data.remote.api.ApiResult
import com.atakaya.weatherapp.data.remote.models.request.CurrentWeatherRequest
import com.atakaya.weatherapp.domain.model.CurrentWeatherForUI
import com.atakaya.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : ViewModel() {
    private val _currentWeather1 = MutableLiveData<ApiResult<CurrentWeatherForUI?>>()

    val currentWeather1: LiveData<ApiResult<CurrentWeatherForUI?>>
        get() = _currentWeather1

    fun getCurrentWeather1() {
        Log.w("atakaya", "search in method")
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentWeatherUseCase.exec(
                CurrentWeatherRequest(
                    lat = "41.07",
                    long = "28.98"
                )
            )
                .catch {
                    Log.e("atakaya", "search incatch", it)
                }
                .collect {
                    Log.w("atakaya", "search in collect ")
                    _currentWeather1.postValue(it)
                }
        }
    }
}
