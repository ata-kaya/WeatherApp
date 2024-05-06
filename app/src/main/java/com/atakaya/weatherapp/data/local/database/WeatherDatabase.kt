package com.atakaya.weatherapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.atakaya.weatherapp.data.Converters
import com.atakaya.weatherapp.data.local.database.dao.WeatherDao
import com.atakaya.weatherapp.data.local.models.WeatherDaoModel

@Database(entities = [WeatherDaoModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
