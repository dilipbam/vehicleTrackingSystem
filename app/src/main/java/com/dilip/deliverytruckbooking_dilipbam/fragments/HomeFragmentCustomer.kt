package com.dilip.deliverytruckbooking_dilipbam.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.R
import com.dilip.deliverytruckbooking_dilipbam.adapter.recyclerViewVehicleAdapterCustomer
import com.dilip.deliverytruckbooking_dilipbam.model.GetVehicle
import com.dilip.deliverytruckbooking_dilipbam.model.GetVehicleDetails
import com.dilip.deliverytruckbooking_dilipbam.model.ListVehicle
import com.dilip.deliverytruckbooking_dilipbam.repository.OwnerRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentCustomer : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private  var lstVehicle = ArrayList<ListVehicle>()
    private lateinit var rdoSix : RadioButton
    private lateinit var rdoTen : RadioButton
    private lateinit var rdoTwe : RadioButton
    private lateinit var btnSearch : Button
    private lateinit var btnContinue : Button

    private var radio : String? = null

    var _id : String? = ""
    var VehicleName : String? = ""
    var VehicleNumber : String? = ""
    var DrivingLicense : String? = ""
    var DrivingName : String? = ""
    var Capacity : String? = ""
    var Rate : Int? = null
    var Radio : String? = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_customer, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        rdoSix = view.findViewById(R.id.rdoSix)
        rdoTen = view.findViewById(R.id.rdoTen)
        rdoTwe = view.findViewById(R.id.rdoTwe)
        btnSearch = view.findViewById(R.id.btnSearch)
        btnContinue = view.findViewById(R.id.btnContinue)

        btnSearch.isEnabled = true


        btnSearch.setOnClickListener {

            getVehicle()



        }
        return view
    }

    private fun getVehicle() {
        if(rdoSix.isChecked)
        {
            radio = rdoSix.text.toString()

        }
        if(rdoTen.isChecked)
        {
            radio = rdoTen.text.toString()
        }
        if(rdoTwe.isChecked)
        {
            radio = rdoTwe.text.toString()
        }

        CoroutineScope(Dispatchers.IO).launch {

            try {
                var radioValue = GetVehicle(Radio = radio)
                val ownerRepository = OwnerRepo()
                val response = ownerRepository.getVehicle(radioValue)
                println("$response")
                MyServiceBuilder.vehicleDetails = (response.vehicleDetails)
                if (response.message == "fetched data") {
                    var vehicleDetails : ArrayList<GetVehicleDetails>? = response.vehicleDetails
                    println("cartDetails $vehicleDetails")

                    if(response.vehicleDetails.isNullOrEmpty())
                    {
                        btnContinue.setBackgroundColor(Color.GRAY)
                        btnContinue.isEnabled = false
//                        delete.isEnabled = false
                        return@launch
                    }
                    else
                    {
                        btnContinue.isEnabled = true
//                        delete.isEnabled = true
                    }
                    for(i in vehicleDetails!!.indices) {
                        var deta: GetVehicleDetails = vehicleDetails[i]
                        print("deta $deta")
                        _id = deta._id
                        VehicleName = deta.VehicleName.toString()
                        VehicleNumber = deta.VehicleNumber.toString()
                        DrivingLicense = deta.DrivingLicense.toString()
                        DrivingName = deta.DrivingName.toString()
                        Capacity = deta.Capacity.toString()
                        Rate = deta.Rate.toString().toInt()
                        Radio = deta.Radio.toString()
                        lstVehicle.add(
                            ListVehicle(
                                _id = _id,
                                VehicleName = VehicleName,
                                VehicleNumber = VehicleNumber,
                                DrivingLicense = DrivingLicense,
                                DrivingName = DrivingName,
                                Capacity = Capacity,
                                Rate = Rate,
                                Radio = Radio,

                            )
                        )
//                        var tinal = finalprice!!.plus(foodPrice!!)
//                        finalprice = tinal


                        withContext(Main) {
                            val context = view?.context
                            val adapter = context?.let { recyclerViewVehicleAdapterCustomer(lstVehicle, it) }
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
                            context,
                            "Cannot fetch data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    makeText(
                        context,
                        ex.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            

        }

        btnSearch.isEnabled = false
    }


}