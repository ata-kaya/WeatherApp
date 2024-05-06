package com.atakaya.weatherapp.utils

object Helper {
    fun getCityId(lat: Double?, lon: Double?): String {
        return "${(lat?.toBigDecimal()?.times(100.toBigDecimal())?.toInt())}${
            (lon?.toBigDecimal()?.times(100.toBigDecimal())?.toInt())
        }"
    }

    fun getLat(cityId: Int?): Double = cityId.toString().substring(0, 4).toInt().div(100.0)


    fun getLon(cityId: Int?): Double = cityId.toString().substring(4, 8).toInt().div(100.0)

}
