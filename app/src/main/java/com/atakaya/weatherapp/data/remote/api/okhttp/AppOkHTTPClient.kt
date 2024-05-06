package com.atakaya.weatherapp.data.remote.api.okhttp

import okhttp3.OkHttpClient

interface AppOkHTTPClient {
    fun getUnsafeOkHTTPClient(): OkHttpClient
}
