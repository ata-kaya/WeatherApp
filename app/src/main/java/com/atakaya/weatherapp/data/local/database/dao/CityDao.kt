package com.atakaya.weatherapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atakaya.weatherapp.data.local.models.CityDaoModel

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCity(cityDaoModel: CityDaoModel)

    @Query("SELECT * FROM city WHERE cityId=:cityId")
    fun getCity(cityId: Int): CityDaoModel?

    @Query("SELECT * FROM city")
    fun getAllCities(): List<CityDaoModel>?
}
