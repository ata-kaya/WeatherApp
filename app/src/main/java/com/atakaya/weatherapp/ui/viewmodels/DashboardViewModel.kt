package com.atakaya.weatherapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atakaya.weatherapp.data.remote.api.ApiResult
import com.atakaya.weatherapp.data.remote.models.request.CurrentWeatherRequest
import com.atakaya.weatherapp.domain.model.CurrentWeatherForUI
import com.atakaya.weatherapp.domain.usecase.GetAllWeathersUseCase
import com.atakaya.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getAllWeathersUseCase: GetAllWeathersUseCase,
) : ViewModel() {

    private val _currentWeather = MutableLiveData<ApiResult<CurrentWeatherForUI?>>()
    private val _allWeathers = MutableLiveData<ApiResult<List<CurrentWeatherForUI?>>>()

    val currentWeather: LiveData<ApiResult<CurrentWeatherForUI?>>
        get() = _currentWeather
    val allWeathers: LiveData<ApiResult<List<CurrentWeatherForUI?>>>
        get() = _allWeathers

    fun getCurrentWeather() {
        Log.i("atakaya", "in method")
        viewModelScope.launch(Dispatchers.IO) {
            getCurrentWeatherUseCase.exec(
                CurrentWeatherRequest(
                    lat = "41.06", long = "28.98"
                )
            ).catch {
                    Log.e("atakaya", "incatch", it)
                }.collect {
                    Log.d("atakaya", "in collect ")
                    _currentWeather.postValue(it)
                }
        }
    }

    fun getAllWeathers() {
        Log.i("atakaya", "ALL in method")
        viewModelScope.launch(Dispatchers.IO) {
            getAllWeathersUseCase.execute().catch {
                    Log.e("atakaya", "incatch", it)
                }.collect {
                    Log.d("atakaya", "in collect of all data $it")
                    _allWeathers.postValue(it)
                }
        }
    }
}
