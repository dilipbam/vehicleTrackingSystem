package com.dilip.deliverytruckbooking_dilipbam.API


import com.dilip.deliverytruckbooking_dilipbam.model.AddVehicle
import com.dilip.deliverytruckbooking_dilipbam.model.GetVehicle
import com.dilip.deliverytruckbooking_dilipbam.model.OwnerRegister
import com.dilip.deliverytruckbooking_dilipbam.model.UpdateDetails
import com.dilip.deliverytruckbooking_dilipbam.response.*
import retrofit2.Response
import retrofit2.http.*

interface OwnerAPI {
    @GET("/getAllOwner")
    suspend fun getAllOwner(
            @Header("Authorization") token: String
    ): Response<GetAllOwnerResponse>



    @POST("register") //this is end point
    suspend fun registerOwner(    //this will hit register api
        @Body ownerRegister: OwnerRegister
    ) : Response<OwnerRegisterResponse>


    @FormUrlEncoded
    @POST("login1")
    suspend fun loginOwner(
        @Field("UserName") UserName: String,
        @Field("Password") Password: String
    ):Response<OwnerLoginResponse>

    @POST("addVehicle")
    suspend fun addVehicle(
            @Body addVehicle: AddVehicle
    ):Response<AddVehicleResponse>

    @POST("getVehicle")
//    @HTTP(method = "GET", path = "getVehicle", hasBody = true)
    suspend fun getVehicle(
        @Body getVehicle: GetVehicle
    ):Response<GetVehicleResponse>




    @PUT("updateDetails")
    suspend fun updateOwner(
        @Body updateDetails: UpdateDetails
    ) : Response<UpdateDetailsResponse>

    @GET("getBookings")
    suspend fun getBookings(

    ) : Response<GetBookingResponseOwner>



}