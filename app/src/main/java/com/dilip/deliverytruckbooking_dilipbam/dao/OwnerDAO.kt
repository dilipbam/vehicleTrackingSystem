package com.dilip.deliverytruckbooking_dilipbam.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dilip.deliverytruckbooking_dilipbam.entity.Owner

@Dao
interface OwnerDAO{
    @Insert
    suspend fun insertOwner(allOwner: ArrayList<Owner>)

    @Query("select * from Owner")
    suspend fun getOwner():MutableList<Owner>
}