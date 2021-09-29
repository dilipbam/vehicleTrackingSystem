package com.dilip.deliverytruckbooking_dilipbam.repository

import android.content.Context
import com.dilip.deliverytruckbooking_dilipbam.API.MyApiRequest
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.API.OwnerAPI
import com.dilip.deliverytruckbooking_dilipbam.database.OwnerDB
import com.dilip.deliverytruckbooking_dilipbam.entity.Owner

class OwnerRepository: MyApiRequest() {
    private val myAPI = MyServiceBuilder.buildService(OwnerAPI::class.java)

    suspend fun getAllOwner(context: Context):MutableList<Owner>{
        val response = apiRequest {
            myAPI.getAllOwner(MyServiceBuilder.token!!)
        }

        var owner = mutableListOf<Owner>()
        if (response.success==true){
            val allOwner: ArrayList<Owner> = response.data!!

            OwnerDB.getInstance(context).clearAllTables()
            OwnerDB.getInstance(context).getOwnerDAO().insertOwner(allOwner)
            owner = OwnerDB.getInstance(context).getOwnerDAO().getOwner()
        }
        return owner
    }
}