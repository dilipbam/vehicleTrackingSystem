package com.dilip.deliverytruckbooking_dilipbam.repository

import com.dilip.deliverytruckbooking_dilipbam.API.CustomerAPI
import com.dilip.deliverytruckbooking_dilipbam.API.MyApiRequest
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.entity.Customer
import com.dilip.deliverytruckbooking_dilipbam.model.CustomerUsername
import com.dilip.deliverytruckbooking_dilipbam.response.DeleteHistoryResponse
import com.dilip.deliverytruckbooking_dilipbam.response.GetBookingResponse
import com.dilip.deliverytruckbooking_dilipbam.response.LoginResponse
import com.dilip.deliverytruckbooking_dilipbam.response.RegistrationResponse

class CustomerRepository: MyApiRequest() {
    private val myAPI = MyServiceBuilder.buildService(CustomerAPI::class.java)

    suspend fun registerCustomer(customer: Customer): RegistrationResponse {
        return apiRequest{
            myAPI.registerCustomer(customer)
        }
    }
    suspend fun login(username: String, password: String):LoginResponse{
        return apiRequest{
            myAPI.login(username,password)
        }
    }

    suspend fun getBooking(Username: String): GetBookingResponse {
        return apiRequest{
            myAPI.getBooking(Username)
        }
    }

    suspend fun deleteFromHistory(customerUsername: CustomerUsername): DeleteHistoryResponse {
        return apiRequest{
            myAPI.deleteFromHistory(customerUsername)
        }
    }


}