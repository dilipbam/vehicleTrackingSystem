package com.dilip.deliverytruckbooking_dilipbam.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Owner(
        val UserName: String? = null,
        val Email: String? = null,
        val Contact: Int? = null,
        val DriverPhoto: String? = null,
        val LicenseNumber: String? = null,
        val Vehicle: String? = null,
        val VehicleType: String? = null,
        val VehiclePhoto: String? = null
)
{
    @PrimaryKey
    var ownerID: Int? = null
}