package com.dilip.deliverytruckbooking_dilipbam.response

import com.dilip.deliverytruckbooking_dilipbam.model.GetBookingDetails

data class GetBookingResponseOwner (
    var message : String? = null,
    var bookingDetails : ArrayList<GetBookingDetails>? = null
        )
