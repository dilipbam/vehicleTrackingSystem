package com.dilip.deliverytruckbooking_dilipbam

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.NotificationClass.NotificationChannels
import com.dilip.deliverytruckbooking_dilipbam.model.UpdateDetails
import com.dilip.deliverytruckbooking_dilipbam.repository.OwnerRepo
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccUpdateActivity : AppCompatActivity() {
    private lateinit var email : EditText
    private lateinit var phoneNumber : EditText
    private lateinit var btnUpdate : Button
    private var sendemail : String? = MyServiceBuilder.ownerEmail
    private var sendphone : String? = MyServiceBuilder.ownerContact

    var checkV = false
    //    var userEmail : String = ""
//    var userPhone :  String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acc_update)
        email = findViewById(R.id.email)
        phoneNumber = findViewById(R.id.phoneNumber)
        btnUpdate = findViewById(R.id.btnUpdate)


        btnUpdate.setOnClickListener {
            var userEmail = email.text.toString()
            var userPhone = phoneNumber.text.toString()
            var username = MyServiceBuilder.ownerUserName.toString()

            var check : Boolean = checkValue(userEmail , userPhone)
            if(check == true)
            {
                CoroutineScope(Dispatchers.IO).launch {
                    println(MyServiceBuilder.ownerUserName)
                    try {
                        var update = UpdateDetails(username = username, userEmail = userEmail, userPhone = userPhone)
                        println(update)
                        val repository = OwnerRepo()
                        val response = repository.updateOwner(update)
                        println("usersfa $userEmail $userPhone")
                        println(" responseeeee $response")
                        if (response.message == "updated") {

                            val snackBar = Snackbar.make(
                                it, "Your Details Updated Succesfully",
                                Snackbar.LENGTH_LONG
                            )
                            snackBar.setActionTextColor(Color.GRAY)
                            val snackBarView = snackBar.view
                            snackBarView.setBackgroundColor(Color.YELLOW)
                            val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                            textView.setTextColor(Color.BLUE)
                            snackBar.show()

                            showLowPriorityNotification()

                            MyServiceBuilder.sendemail = response.userEmail
                            MyServiceBuilder.sendphone = response.userPhone
                            println("after update button cliked email ${MyServiceBuilder.sendemail}")
                            println("after update button clicked phone ${MyServiceBuilder.sendphone}")
                            saveDetails()


                        }
                        else {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    this@AccUpdateActivity,
                                    "Cannot fetch data",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@AccUpdateActivity,
                                ex.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            else
            {
                errorHandling(userEmail, userPhone)
            }
        }
    }
    private fun showLowPriorityNotification(){
        val notificationManager = NotificationManagerCompat.from(this)
        val notificationChannels = NotificationChannels(this)
        notificationChannels.createNotificationChannels()
        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_2)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Notification")
            .setContentText("Owner details has been updated successfully")
            .setColor(Color.BLUE)
            .build()
        notificationManager.notify(2, notification)
    }

    private fun saveDetails() {
        val sendemail = MyServiceBuilder.sendemail
        val sendphone = MyServiceBuilder.sendphone
        println("emailllllllphoneee  $sendemail, $sendphone")
        val sharedPref = getSharedPreferences("SaveDetailsPref", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("sendemail", sendemail)
        editor.putString("sendphone", sendphone)
        editor.apply()
    }

    private fun errorHandling(userEmail: String, userPhone: String) {
        if(userEmail.length < 1)
        {
            email.requestFocus()
            email.setError("Field cannot be empty")
        }
        if(userPhone.length < 1)
        {
            phoneNumber.requestFocus()
            phoneNumber.setError("Field cannot be empty")
        }
    }
    fun checkValue(userEmail: String, userPhone: String): Boolean {
        if(userEmail.length > 1  && userPhone.length > 1)
        {
            checkV = true
            println("true $checkV")
        }
        else
        {
            checkV = false
        }
        return checkV
    }
}