package com.atakaya.weatherapp.data.remote.api.okhttp

import android.annotation.SuppressLint
import com.atakaya.weatherapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Inject
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class OkHTTPClientImpl @Inject constructor() : AppOkHTTPClient {

    override fun getUnsafeOkHTTPClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                requestBuilder.addHeader("X-RapidAPI-Key", BuildConfig.XRapidAPIKey)
                requestBuilder.addHeader("X-RapidAPI-Host", BuildConfig.XRapidAPIHost)
                chain.proceed(requestBuilder.build())
            }
            try {
                val trustAllCerts = arrayOf<TrustManager>(@SuppressLint("CustomX509TrustManager")
                object : X509TrustManager {
                    @SuppressLint("TrustAllX509TrustManager")
                    override fun checkClientTrusted(
                        p0: Array<out X509Certificate>?, p1: String?
                    ) {
                    }

                    @SuppressLint("TrustAllX509TrustManager")
                    override fun checkServerTrusted(
                        p0: Array<out X509Certificate>?, p1: String?
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()

                })
                SSLContext.getInstance("TLS").apply {
                    init(null, trustAllCerts, SecureRandom())
                    sslSocketFactory(socketFactory, trustAllCerts[0] as X509TrustManager)
                    hostnameVerifier { _, _ -> true }
                }

            } catch (e: Exception) {
                throw java.lang.RuntimeException(e)
            }
        }.build()
    }
}
