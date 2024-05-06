package com.atakaya.weatherapp.data.remote.api

sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>
    data class Error(val exception: Throwable) : ApiResult<Nothing>
    data object Loading : ApiResult<Nothing>
}
