package com.dilip.deliverytruckbooking_dilipbam.repository

import com.dilip.deliverytruckbooking_dilipbam.API.BookAPI
import com.dilip.deliverytruckbooking_dilipbam.API.CustomerAPI
import com.dilip.deliverytruckbooking_dilipbam.API.MyApiRequest
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.entity.Customer
import com.dilip.deliverytruckbooking_dilipbam.model.AddBooking
import com.dilip.deliverytruckbooking_dilipbam.response.BookingResponse
import com.dilip.deliverytruckbooking_dilipbam.response.LoginResponse
import com.dilip.deliverytruckbooking_dilipbam.response.OwnerBookingResponse
import com.dilip.deliverytruckbooking_dilipbam.response.RegistrationResponse

class BookingRepository: MyApiRequest() {
    private val myAPI = MyServiceBuilder.buildService(BookAPI::class.java)


    suspend fun booking(addBooking: AddBooking): BookingResponse {
        return apiRequest{
            myAPI.booking(addBooking)
        }
    }


    suspend fun bookingOwner(addBooking: AddBooking): OwnerBookingResponse {
        return apiRequest{
            myAPI.bookingOwner(addBooking)
        }
    }


}