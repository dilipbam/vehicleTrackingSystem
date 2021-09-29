package com.dilip.deliverytruckbooking_dilipbam.response

import com.dilip.deliverytruckbooking_dilipbam.model.GetVehicleDetails

data class GetVehicleResponse (
    val message : String? = null,
    val vehicleDetails : ArrayList<GetVehicleDetails>? = null

        )
