package com.dilip.deliverytruckbooking_dilipbam

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.model.AddVehicle
import com.dilip.deliverytruckbooking_dilipbam.repository.CustomerRepository
import com.dilip.deliverytruckbooking_dilipbam.repository.OwnerRepo
import com.dilip.deliverytruckbooking_dilipbam.ui.BottomActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AddVehicleAcitivity : AppCompatActivity() {
    private lateinit var etVehicleName : EditText
    private lateinit var rdoSix : RadioButton
    private lateinit var rdoTen : RadioButton
    private lateinit var rdoTwe : RadioButton
    private lateinit var etVehicleNumber : EditText
    private lateinit var etDriverLicense : EditText
    private lateinit var etDriverName : EditText
    private lateinit var etCapacity : EditText
    private lateinit var etRate : EditText
    private lateinit var btnAdd : Button
    private var radio : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vehicle_acitivity)

        etVehicleName = findViewById(R.id.etVehicleName)
        rdoSix = findViewById(R.id.rdoSix)
        rdoTen = findViewById(R.id.rdoTen)
        rdoTwe = findViewById(R.id.rdoTwe)
        etVehicleNumber = findViewById(R.id.etVehicleNumber)
        etDriverLicense = findViewById(R.id.etDriverlicense)
        etDriverName = findViewById(R.id.etDrivername)
        etCapacity = findViewById(R.id.etCapacity)
        etRate = findViewById(R.id.etRate)
        btnAdd = findViewById(R.id.addVehicle)


        btnAdd.setOnClickListener {

            var vName = etVehicleName.text.toString()
            var vNumber = etVehicleNumber.text.toString()
            var drivingLicense = etDriverLicense.text.toString()
            var drivingName = etDriverName.text.toString()
            var capacity = etCapacity.text.toString()
            var rate = etRate.text.toString().toInt()

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


            var addVehicle = AddVehicle(
                VehicleName = vName,
                VehicleNumber = vNumber,
                DrivingLicense = drivingLicense,
                DrivingName = drivingName,
                Capacity = capacity,
                Rate = rate,
                Radio = radio
            )

            CoroutineScope(Dispatchers.IO).launch{
                try{
                    val  repository = OwnerRepo()
                    val response = repository.addVehicle(addVehicle) //need changes here and in api to work

                    if(response.message== "Added Vehicle Successfully"){

                        withContext(Dispatchers.Main) {

                            val snackBar = Snackbar.make(
                                it, "Succesfully Added your Vehicle",
                                Snackbar.LENGTH_LONG
                            )
                            snackBar.setActionTextColor(Color.GRAY)
                            val snackBarView = snackBar.view
                            snackBarView.setBackgroundColor(Color.YELLOW)
                            val textView =
                                snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                            textView.setTextColor(Color.BLUE)
                            snackBar.show()
                        }
                    }
                    else{
                        withContext(Dispatchers.Main){
                            val snack = make(
                                it,
                                "Invalid Credentials",
                                Snackbar.LENGTH_LONG
                            )
                            snack.setAction("Ok"){
                                snack.dismiss()
                            }
                            snack.show()
                        }
                    }
                }
                catch (ex: Exception){
                    withContext(Dispatchers.Main){
                        withContext(Dispatchers.Main){
                            val snack = make(
                                it,
                                "Invalid Credentials",
                                Snackbar.LENGTH_LONG
                            )
                            snack.setAction("Ok"){
                                snack.dismiss()
                            }
                            snack.show()
                        }
                    }
                }
            }


        }
    }


}

