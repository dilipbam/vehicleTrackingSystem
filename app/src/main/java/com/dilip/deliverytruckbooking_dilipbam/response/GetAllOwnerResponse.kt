package com.dilip.deliverytruckbooking_dilipbam.response

import com.dilip.deliverytruckbooking_dilipbam.entity.Owner

data class GetAllOwnerResponse (
    val success: Boolean? = null,
    val data: ArrayList<Owner>? = null
)