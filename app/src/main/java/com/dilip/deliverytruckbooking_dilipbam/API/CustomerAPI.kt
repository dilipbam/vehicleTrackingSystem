package com.dilip.deliverytruckbooking_dilipbam.API

import com.dilip.deliverytruckbooking_dilipbam.entity.Customer
import com.dilip.deliverytruckbooking_dilipbam.model.CustomerUsername
import com.dilip.deliverytruckbooking_dilipbam.response.DeleteHistoryResponse
import com.dilip.deliverytruckbooking_dilipbam.response.GetBookingResponse
import com.dilip.deliverytruckbooking_dilipbam.response.LoginResponse
import com.dilip.deliverytruckbooking_dilipbam.response.RegistrationResponse
import retrofit2.Response
import retrofit2.http.*

interface CustomerAPI {
    @POST("customerRegister")
    suspend fun registerCustomer(
        @Body customer: Customer
    ): Response<RegistrationResponse>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ):Response<LoginResponse>

    @FormUrlEncoded
    @POST("getBooking")
    suspend fun getBooking(
        @Field("Username") Username: String

    ):Response<GetBookingResponse>

    @HTTP(method = "DELETE", path = "deleteHistory", hasBody = true)
    suspend fun deleteFromHistory(
       @Body customerUsername : CustomerUsername

    ):Response<DeleteHistoryResponse>


}