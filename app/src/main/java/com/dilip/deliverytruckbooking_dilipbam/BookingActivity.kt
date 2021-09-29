package com.dilip.deliverytruckbooking_dilipbam

import android.app.DatePickerDialog
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.NotificationClass.NotificationChannels
import com.dilip.deliverytruckbooking_dilipbam.model.AddBooking
import com.dilip.deliverytruckbooking_dilipbam.repository.BookingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class BookingActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var pickupLocation : EditText
    private lateinit var deliveryLocation : EditText
    private lateinit var etContact : EditText
    private lateinit var chooseDate : EditText
    private lateinit var spinner : Spinner
    private lateinit var btnBook : Button
    private lateinit var vName : TextView
    private lateinit var vNumber : TextView
    private lateinit var dlicense : TextView
    private lateinit var dName : TextView
    private lateinit var capacity : TextView
    private lateinit var rate : TextView
    private lateinit var type : TextView
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null
    private lateinit var asensor : TextView

    private var selectionItem : String? = null
    private val paymentType = arrayOf("Cash on Delivery", "eSewa", "Khalti")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        pickupLocation = findViewById(R.id.pickupLocation)
        deliveryLocation = findViewById(R.id.deliveryLocation)
        etContact = findViewById(R.id.etContact)
        chooseDate = findViewById(R.id.chooseDate)
        spinner = findViewById(R.id.spinner)
        btnBook = findViewById(R.id.btnBook)
        vName = findViewById(R.id.vname)
        vNumber = findViewById(R.id.vNumber)
        dlicense = findViewById(R.id.dlicense)
        dName   = findViewById(R.id.dname)
        capacity = findViewById(R.id.capacity)
        rate = findViewById(R.id.rate)
        type = findViewById(R.id.type)

        sensorManager = this.getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager;


        //Array adapter
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            paymentType
        )
        //Setting adapter to spinner's adapter
        spinner.adapter = adapter

        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectionItem = parent?.getItemAtPosition(position).toString()

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        
        chooseDate.setOnClickListener { 
            loadCalendar()
        }

        btnBook.setOnClickListener {
            checkValid()
            ShowHighPriorityNotification()
        }

         var VehicleName = intent.getStringExtra("name")
         var VehicleNumber = intent.getStringExtra("number")
         var DrivingLicense = intent.getStringExtra("dliscense")
        println("drivingl $DrivingLicense")
         var DrivingName = intent.getStringExtra("dname")
         var Capacity = intent.getStringExtra("capacity")
         var Rate = intent.getStringExtra("rate")
         var Radio = intent.getStringExtra("type")


        vName.text = VehicleName
        vNumber.text = VehicleNumber
        dlicense.text = DrivingLicense
        dName.text = DrivingName
        capacity.text = Capacity
        rate.text = Rate
        type.text = Radio




        println("$VehicleName")


        if(!checkSensor()){
            return
        }
        else{
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    private fun checkSensor(): Boolean {
        var flag =true
        if(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) == null){
            flag = false
        }
        return flag
    }


    private fun checkValid() {
        if(pickupLocation.length()< 10){
            pickupLocation.requestFocus()
            pickupLocation.setError("Enter your full pickup location")
        }
        else if (deliveryLocation.length()<10){
            deliveryLocation.requestFocus()
            deliveryLocation.setError("Enter your full delivery location")
            }
        else if (etContact.length()<10){
            etContact.requestFocus()
            etContact.setError("Enter your contact number")
        }
        else {
            addBookings()
        }
    }

    private fun addBookings() {

        CoroutineScope(Dispatchers.IO).launch {

            var pickLocation = pickupLocation.text.toString()
            var deliverLocation = deliveryLocation.text.toString()
            var phone = etContact.text.toString()
            var date = chooseDate.text.toString()
            var username = MyServiceBuilder.customerUsername

            var addBooking = AddBooking(
                Username = username,
                VehicleName = vName.text.toString(),
                VehicleNumber = vNumber.text.toString(),
                DrivingLicense = dlicense.text.toString() ,
                DrivingName = dName.text.toString(),
                Capacity = capacity.text.toString() ,
                Rate = rate.text.toString().toInt(),
                Radio = type.text.toString(),
                PickLocation = pickLocation,
                DeliverLocation = deliverLocation,
                Phone = phone,
                Date = date

            )


                var bookingRepository = BookingRepository()
                var bookingOwner = BookingRepository()
                var response = bookingRepository.booking(addBooking)
            var responseOwner = bookingOwner.bookingOwner(addBooking)
                println("response $response")
                if (response.message == "success") {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@BookingActivity,
                            "You have successfully booked Truck",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    println("order has been placed")
                }
                else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@BookingActivity,
                            "Cannot place booking",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            if (responseOwner.message == "success") {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@BookingActivity,
                        "Success for Owner History",
                        Toast.LENGTH_LONG
                    ).show()
                }
                println("order has been placed")
            }
            else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@BookingActivity,
                        "Cannot place booking",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            }
        }

    private fun ShowHighPriorityNotification(){
            val notificationManager = NotificationManagerCompat.from(this)
            val notificationChannels = NotificationChannels(this)
            notificationChannels.createNotificationChannels()
            val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Notification")
                .setContentText("Truck booked successfully")
                .setColor(Color.BLUE)
                .build()
            notificationManager.notify(1, notification)
        }


    private fun loadCalendar(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        
        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener{view, year, monthOfYear, dayOfMonth ->
                var month = monthOfYear + 1
                chooseDate.setText("$dayOfMonth/$month/$year").toString()
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    override fun onSensorChanged(event: SensorEvent?) {

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}