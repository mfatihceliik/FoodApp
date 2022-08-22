package com.muhammedfatihcelik.foodorder.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammedfatihcelik.foodorder.data.entity.address.City
import com.muhammedfatihcelik.foodorder.data.entity.address.CityResponse
import com.muhammedfatihcelik.foodorder.data.entity.address.District
import com.muhammedfatihcelik.foodorder.data.entity.address.DistrictResponse

@Dao
interface LocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCity(vararg city: City)

    @Query("SELECT * FROM City")
    suspend fun getCities(): List<City>

    @Insert
    suspend fun insertAllDistrict(vararg district: District)

    @Query("SELECT * FROM District")
    suspend fun getDistricts(): List<District>

    @Query("SELECT * FROM District WHERE District.cityId IN (:cityId)")
    suspend fun getDistrictByCityId(cityId: Int): List<District>

}