package com.atakaya.weatherapp.utils.extensions

import com.atakaya.weatherapp.data.local.models.CityDaoModel
import com.atakaya.weatherapp.data.remote.models.response.Location
import com.atakaya.weatherapp.utils.Helper

fun Location?.getCityModel(): CityDaoModel {
    val latlon = Helper.getCityId(this?.lat, this?.lon)
    return CityDaoModel(
        cityId = latlon.toInt(), cityName = "${this?.name}/${this?.region}/${this?.country}"
    )
}

fun CityDaoModel.getLocationModel(): Location {
    return Location(
        lat = Helper.getLat(this.cityId),
        lon = Helper.getLon(this.cityId),
        name = this.cityName.split("/")[0],
        region = this.cityName.split("/")[1],
        country = this.cityName.split("/")[2],
    )
}
