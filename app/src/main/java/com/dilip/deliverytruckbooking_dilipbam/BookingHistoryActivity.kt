package com.dilip.deliverytruckbooking_dilipbam

import android.content.Intent
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.adapter.GetHistoryAdapter
import com.dilip.deliverytruckbooking_dilipbam.adapter.recyclerViewVehicleAdapterCustomer
import com.dilip.deliverytruckbooking_dilipbam.model.*
import com.dilip.deliverytruckbooking_dilipbam.repository.CustomerRepository
import com.dilip.deliverytruckbooking_dilipbam.repository.OwnerRepo
import com.dilip.deliverytruckbooking_dilipbam.ui.BottomActivity
import com.dilip.deliverytruckbooking_dilipbam.ui.DashboardActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookingHistoryActivity : AppCompatActivity() {
    var VehicleName : String? = ""
    var VehicleNumber : String? = ""
    var DrivingLicense : String? = ""
    var DrivingName : String? = ""
    var Capacity : String? = ""
    var Rate : Int? = null
    var Radio : String? = ""
    var PickLocation : String? = ""
    var DeliverLocation : String? = null
    var Phone : String? = ""
    var Date : String? = ""
    var lstBookDetails  = ArrayList<ListBookDetails>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var deleteHistory : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_history)
        recyclerView = findViewById(R.id.recyclerView)
        deleteHistory = findViewById(R.id.deleteHistory)

        getBooking()

        deleteHistory.setOnClickListener{

            deleteHis()

        }
    }

    private fun deleteHis() {

        val username = MyServiceBuilder.customerUsername.toString()

        println(username)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    var customerUsername = CustomerUsername(Username = username )
                    val customerRepository = CustomerRepository()
                    val response = customerRepository.deleteFromHistory(customerUsername)
                    println(" response $response")
                    if (response.message == "deleted") {

                        val snackBar = Snackbar.make(
                            this@BookingHistoryActivity.findViewById(android.R.id.content),
                            "History Deleted", Snackbar.LENGTH_LONG
                        )
                        snackBar.show()

                        withContext(Dispatchers.Main) {
                            startActivity(Intent(this@BookingHistoryActivity, DashboardActivity::class.java ))
                            Toast.makeText(
                                this@BookingHistoryActivity,
                                "",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@BookingHistoryActivity,
                                "Cannot fetch data",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        makeText(
                            this@BookingHistoryActivity,
                            ex.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }


        }

    private fun getBooking() {
        CoroutineScope(Dispatchers.IO).launch {

            val Username = MyServiceBuilder.customerUsername.toString()

            println("username, $Username")

            try {

                val customerRepository = CustomerRepository()
                val response = customerRepository.getBooking(Username)
                println("$response")
                MyServiceBuilder.BookDetails = (response.BookDetails)
                if (response.message == "fetched data") {
                    var BookDetails : ArrayList<GetBookDetails>? = response.BookDetails
                    println("cartDetails $BookDetails")

//                    if(response.vehicleDetails.isNullOrEmpty())
//                    {
//                        btnContinue.setBackgroundColor(Color.GRAY)
//                        btnContinue.isEnabled = false
////                        delete.isEnabled = false
//                        return@launch
//                    }
//                    else
//                    {
//                        btnContinue.isEnabled = true
////                        delete.isEnabled = true
//                    }
                    for(i in BookDetails!!.indices) {
                        var deta: GetBookDetails = BookDetails[i]
                        print("deta $deta")



                        VehicleName = deta.VehicleName.toString()
                        VehicleNumber = deta.VehicleNumber.toString()
                        DrivingLicense = deta.DrivingLicense.toString()
                        DrivingName = deta.DrivingName.toString()
                        Capacity = deta.Capacity.toString()
                        Rate = deta.Rate.toString().toInt()
                        Radio = deta.Type.toString()
                        PickLocation = deta.PickLocation.toString()
                        DeliverLocation = deta.DeliverLocation.toString()
                        Phone = deta.Phone.toString()
                        Date = deta.Date.toString()

                        lstBookDetails.add(
                            ListBookDetails(
                                VehicleName = VehicleName,
                                VehicleNumber = VehicleNumber,
                                DrivingLicense = DrivingLicense,
                                DrivingName = DrivingName,
                                Capacity = Capacity,
                                Rate = Rate,
                                Type = Radio,
                                PickLocation = PickLocation,
                                DeliverLocation = DeliverLocation,
                                Phone = Phone,
                                Date = Date

                                )
                        )
//                        var tinal = finalprice!!.plus(foodPrice!!)
//                        finalprice = tinal


                        withContext(Dispatchers.Main) {
                            val context = this@BookingHistoryActivity
                            val adapter =  GetHistoryAdapter(lstBookDetails, context)
                            recyclerView.layoutManager = LinearLayoutManager(context)
                            recyclerView.adapter = adapter
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
                            this@BookingHistoryActivity,
                            "Cannot fetch data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@BookingHistoryActivity,
                        ex.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }
    }
}