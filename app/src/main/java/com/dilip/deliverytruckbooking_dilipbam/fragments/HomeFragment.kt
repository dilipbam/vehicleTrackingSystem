package com.dilip.deliverytruckbooking_dilipbam.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.R
import com.dilip.deliverytruckbooking_dilipbam.adapter.BookingHistoryOwnerAdapter
import com.dilip.deliverytruckbooking_dilipbam.adapter.recyclerViewVehicleAdapterCustomer
import com.dilip.deliverytruckbooking_dilipbam.model.*
import com.dilip.deliverytruckbooking_dilipbam.repository.OwnerRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {

    var _id : String? = ""
    var VehicleName : String? = ""
    var VehicleNumber : String? = ""
    var DrivingLicense : String? = ""
    var DrivingName : String? = ""
    var Capacity : String? = ""
    var Rate : Int? = null
    var Radio : String? = ""
    var Username : String? = ""

    private lateinit var recyclerview: RecyclerView
    private var lstBookingHistoryOwner = ArrayList<ListBookingHistoryOwner>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        recyclerview = view.findViewById(R.id.recyclerview)

        getBooking()
        return view
    }

    private fun getBooking() {
        CoroutineScope(Dispatchers.IO).launch {

            try {

                val ownerRepository = OwnerRepo()
                val response = ownerRepository.getBookings()
                println("$response")
//                MyServiceBuilder.bookingsDetails = (response.bookingsDetails)
                if (response.message == "fetched data") {
                    var bookingsDetails : ArrayList<GetBookingDetails>? = response.bookingDetails
                    println("cartDetails $bookingsDetails")

                    for(i in bookingsDetails!!.indices) {
                        var deta: GetBookingDetails = bookingsDetails[i]
                        print("deta $deta")
                        Username = deta.UserName.toString()
                        VehicleName = deta.VehicleName.toString()
                        VehicleNumber = deta.VehicleNumber.toString()
                        DrivingLicense = deta.DrivingLicense.toString()
                        DrivingName = deta.DrivingName.toString()
                        Capacity = deta.Capacity.toString()
                        Rate = deta.Rate.toString().toInt()
                        Radio = deta.Type.toString()
                        var pick = deta.PickLocation.toString()
                        var drop = deta.DeliverLocation.toString()
                        var date = deta.Date.toString()
                        var phone = deta.Phone.toString()
                        lstBookingHistoryOwner.add(
                            ListBookingHistoryOwner(
                                Username = Username,
                                VehicleName = VehicleName,
                                VehicleNumber = VehicleNumber,
                                DrivingLicense = DrivingLicense,
                                DrivingName = DrivingName,
                                Capacity = Capacity,
                                Rate = Rate,
                                Type = Radio,
                                PickLocation = pick,
                                DeliverLocation = drop ,
                                Phone = phone,
                                Date = date


                                )
                        )
//
                        withContext(Dispatchers.Main) {
                            val context = view?.context
                            val adapter = context?.let { BookingHistoryOwnerAdapter(lstBookingHistoryOwner, it) }
                            recyclerview.layoutManager = LinearLayoutManager(context)
                            recyclerview.adapter = adapter
                        }

                    }
//                    totalPrice.text = ("Rs. ${finalprice.toString()}")
//                    grandTotal.text = "Rs. ${(finalprice!! + 50 ).toString()}"
//                    var grandTotal = grandTotal.text.toString()
//                    ServiceBuilder.finalPrice = grandTotal

                    println("data fetched")
                }
                else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "Cannot fetch data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        ex.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }
    }


}