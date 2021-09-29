package com.dilip.deliverytruckbooking_dilipbam.API

import com.dilip.deliverytruckbooking_dilipbam.model.GetBookDetails
import com.dilip.deliverytruckbooking_dilipbam.model.GetVehicleDetails
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyServiceBuilder {
    private const val BASE_URL = "http://10.0.2.2:90/"
    var token: String? = null
    var ownerName: String? = null
    var ownerEmail: String? = null
    var ownerContact: String? = null
    var ownerUserName: String? = null
    var vehicleDetails : ArrayList<GetVehicleDetails>? = null
    var BookDetails : ArrayList<GetBookDetails>? = null
    var customerName : String? = null
    var customerUsername : String? = null
    var customerEmail : String? = null
    var customerPhone : String? = null
    var sendemail : String? = null
    var sendphone : String? = null
    var updatedEmail : String? = null
    var updatedPhone : String? = null
    private val okHttpClient = OkHttpClient.Builder()
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())

    private val retrofit = retrofitBuilder.build()

    fun  <T> buildService(serviceType: Class<T>):T {
        return retrofit.create(serviceType)
    }

}