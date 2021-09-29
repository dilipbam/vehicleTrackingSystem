package com.dilip.deliverytruckbooking_dilipbam.repository

import com.dilip.deliverytruckbooking_dilipbam.API.MyApiRequest
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.API.OwnerAPI
import com.dilip.deliverytruckbooking_dilipbam.model.AddVehicle
import com.dilip.deliverytruckbooking_dilipbam.model.GetVehicle
import com.dilip.deliverytruckbooking_dilipbam.model.OwnerRegister
import com.dilip.deliverytruckbooking_dilipbam.model.UpdateDetails
import com.dilip.deliverytruckbooking_dilipbam.response.*

class OwnerRepo
    : MyApiRequest() {
    val myApi =
        MyServiceBuilder.buildService(OwnerAPI::class.java)
    suspend fun registerOwner(ownerRegister: OwnerRegister): OwnerRegisterResponse {
        return apiRequest {
            myApi.registerOwner(ownerRegister)
        }
    }

    suspend fun loginOwner(UserName: String, Password: String): OwnerLoginResponse {
        return apiRequest{
            myApi.loginOwner(UserName,Password)
        }
    }

    suspend fun addVehicle(addVehicle: AddVehicle): AddVehicleResponse {
        return apiRequest{
            myApi.addVehicle(addVehicle)
        }
    }

    suspend fun getVehicle(getVehicle: GetVehicle): GetVehicleResponse {
        return apiRequest{
            myApi.getVehicle(getVehicle)
        }
    }


    suspend fun getBookings(): GetBookingResponseOwner {
        return apiRequest{
            myApi.getBookings()
        }
    }

    suspend fun updateOwner(update: UpdateDetails): UpdateDetailsResponse {
        return apiRequest {
            myApi.updateOwner(update)
        }
    }

}