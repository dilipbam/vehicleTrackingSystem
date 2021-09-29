package com.dilip.deliverytruckbooking_dilipbam.response

import com.dilip.deliverytruckbooking_dilipbam.model.GetBookDetails

data class GetBookingResponse (
    var message : String? =null,
    val BookDetails : ArrayList<GetBookDetails>? =null
        )
