package com.dilip.deliverytruckbooking_dilipbam.model

data class ListBookingHistoryOwner(
    var Username : String? = null,
    var VehicleName : String? = null,
    var VehicleNumber : String? = null,
    var DrivingLicense : String? = null,
    var DrivingName : String? = null,
    var Capacity : String? = null,
    var Rate : Int? = null,
    var Type : String? = null,
    var PickLocation: String? = null,
    var DeliverLocation: String? = null,
    var Phone: String? = null,
    var Date: String? = null
)