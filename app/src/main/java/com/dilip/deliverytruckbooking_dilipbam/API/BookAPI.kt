package com.dilip.deliverytruckbooking_dilipbam.API

import com.dilip.deliverytruckbooking_dilipbam.model.AddBooking
import com.dilip.deliverytruckbooking_dilipbam.model.OwnerRegister
import com.dilip.deliverytruckbooking_dilipbam.response.BookingResponse
import com.dilip.deliverytruckbooking_dilipbam.response.OwnerBookingResponse
import com.dilip.deliverytruckbooking_dilipbam.response.OwnerRegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BookAPI {

    @POST("addBooking") //this is end point
    suspend fun booking(    //this will hit register api
        @Body addBooking: AddBooking
    ) : Response<BookingResponse>

    @POST("addBookingOwner") //this is end point
    suspend fun bookingOwner(    //this will hit register api
        @Body addBooking: AddBooking
    ) : Response<OwnerBookingResponse>
}