package com.atakaya.weatherapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atakaya.weatherapp.data.local.database.dao.CityDao
import com.atakaya.weatherapp.data.local.models.CityDaoModel

@Database(entities = [CityDaoModel::class], version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
