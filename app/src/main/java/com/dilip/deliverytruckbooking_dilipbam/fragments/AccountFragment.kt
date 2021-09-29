package com.dilip.deliverytruckbooking_dilipbam.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.AccUpdateActivity
import com.dilip.deliverytruckbooking_dilipbam.AddVehicleAcitivity
import com.dilip.deliverytruckbooking_dilipbam.BookingHistoryActivity
import com.dilip.deliverytruckbooking_dilipbam.R
import com.dilip.deliverytruckbooking_dilipbam.ui.LoginActivity
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

class AccountFragment : Fragment(), SensorEventListener {
    private lateinit var imgProfile: CircleImageView
    private lateinit var fullName: TextView
    private lateinit var email: TextView
    private lateinit var phone: TextView
    private lateinit var editProfile: Button

    private lateinit var order: TextView
    private lateinit var logout: TextView
    private lateinit var addVehicle : TextView
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        imgProfile = view.findViewById(R.id.imgProfile)
        fullName = view.findViewById(R.id.fullName)
        email = view.findViewById(R.id.email)
        phone = view.findViewById(R.id.phone)
        editProfile = view.findViewById(R.id.editProfile)
        order = view.findViewById(R.id.order)
        logout = view.findViewById(R.id.logout)
        fullName.text = (MyServiceBuilder.ownerName)
        addVehicle = view.findViewById(R.id.addVehicle)
        imgProfile.setOnClickListener {
//            loadPopUpMenu()
        }

        sensorManager = context?.getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager;



        showDetails()
        email.text = (MyServiceBuilder.ownerEmail)
        phone.text =(MyServiceBuilder.ownerContact)

        logout.setOnClickListener {
            logout()
        }
        editProfile.setOnClickListener {
            editProfile()
        }
        order.setOnClickListener {
            orderHistory()
        }

        addVehicle.setOnClickListener {

            addVehicles()
        }
        QuichUpdateData()

        if (!checkSensor())
        {
            return null
        }
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

        email.text = MyServiceBuilder.sendemail
        phone.text = MyServiceBuilder.sendphone


        return view
    }
    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) == null) {
            flag = false
        }
        return flag
    }

    private fun addVehicles() {
        startActivity(Intent(context, AddVehicleAcitivity::class.java))
    }


    private fun orderHistory() {
        startActivity(Intent(context, BookingHistoryActivity::class.java))
    }

        private fun showDetails() {
        val sharedPref = context?.getSharedPreferences("UsernamePasswordPref", Context.MODE_PRIVATE)
        val email = sharedPref?.getString("email", "")
        val phone = sharedPref?.getString("phone", "")
        println("sdfsadfasdfgfdhlqwerqwerqwer $email , $phone")
        MyServiceBuilder.updatedEmail = email
        MyServiceBuilder.updatedPhone = phone
    }
    private fun QuichUpdateData() {
        val sharedPref = context?.getSharedPreferences("SaveDetailsPref", Context.MODE_PRIVATE)
        val email1 = sharedPref?.getString("sendemail", "")
        val phone1 = sharedPref?.getString("sendphone", "")
        println("after updating and clicking account fragment $email1 , $phone1")
        MyServiceBuilder.sendemail = email1
        MyServiceBuilder.sendphone = phone1
        email.text = email1
        phone.text = phone1

    }
    private fun editProfile() {
        startActivity(Intent(context, AccUpdateActivity::class.java))
    }

    private fun logout() {
        var builder = AlertDialog.Builder(view?.context!!)
        builder.setTitle("Alert")
        builder.setMessage("Are you sure want to Logout?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            context?.getSharedPreferences("UsernamePasswordPref", Context.MODE_PRIVATE)?.edit()
                ?.clear()?.apply();
            startActivity(Intent(context, LoginActivity::class.java))
        }
        builder.setNeutralButton("Cancel") { dialogInterface, which ->
            return@setNeutralButton
        }
        builder.setNegativeButton("No") { dialogInterface, which ->
            return@setNegativeButton
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()

        //logout
    }
    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[0]
        if (values < sensor!!.maximumRange)
            logout()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
}