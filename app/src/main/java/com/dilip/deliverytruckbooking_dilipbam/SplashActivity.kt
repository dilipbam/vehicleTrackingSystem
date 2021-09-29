package com.dilip.deliverytruckbooking_dilipbam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import android.widget.Toast
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.repository.CustomerRepository
import com.dilip.deliverytruckbooking_dilipbam.repository.OwnerRepo
import com.dilip.deliverytruckbooking_dilipbam.ui.BottomActivity
import com.dilip.deliverytruckbooking_dilipbam.ui.DashboardActivity
import com.dilip.deliverytruckbooking_dilipbam.ui.LoginActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception

class SplashActivity : AppCompatActivity() {
//    private var sendemail : String? = MyServiceBuilder.sendemail
//    private var sendphone : String? = MyServiceBuilder.sendphone
    private val SPLASH_DELAY: Long = 1000 //3 seconds
    private var mDelayHandler: Handler? = null
    private var progressBarStatus = 0
    var dummy:Int = 0
    private lateinit var splash_screen_progress_bar : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        splash_screen_progress_bar = findViewById(R.id.splash_screen_progress_bar)
        mDelayHandler = Handler()
        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }
    private fun checkLogin() {
        val sharedPref = getSharedPreferences("UsernamePasswordPref", MODE_PRIVATE)
        val username = sharedPref.getString("username","")
        val password = sharedPref.getString("password","")
//        val fullName = sharedPref.getString("fullName" , "")
//        val email = sharedPref.getString("email" , "")
//        val phone = sharedPref.getString("phone" , "")
//        val usernamee = sharedPref.getString("username", "")
//        println("username $usernamee")
        CoroutineScope(Dispatchers.IO).launch {
            if(username== null && password ==null){
                withContext(Main){
                    println("null")
                    startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                    finish()
                }
            }else{
                try {
                    val repository= OwnerRepo()
                    val response = repository.loginOwner(username.toString(),password.toString())
                    if (response.message == "Authorization success"){
                        MyServiceBuilder.token = "Bearer ${response.token}"
//                        ServiceBuilder.customerId = response.customerID
//                        ServiceBuilder.customerName = response.customerName
//                        ServiceBuilder.customerEmail = response.customerEmail
//                        ServiceBuilder.customerPhone = response.customerPhone
//                        ServiceBuilder.username = response.username
                        startActivity(
                            Intent(
                                this@SplashActivity,
                                BottomActivity::class.java
                            )
                        )
                        finish()
                    }
                }catch (ex : IOException){
                    withContext(Dispatchers.Main) {
                    println("")
                    }
                }
            }
        }
    }

    private fun launchLoginActivity() {
        var intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        this.finish()
        mDelayHandler!!.removeCallbacks(mRunnable)
    }
    private val mRunnable: Runnable = Runnable {
        Thread(Runnable {
            while (progressBarStatus < 100) {
                // performing some dummy operation
                try {
                    dummy = dummy+1
                    Thread.sleep(10)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                // tracking progress
                progressBarStatus = dummy
                // Updating the progress bar
                splash_screen_progress_bar.progress = progressBarStatus
            }
            splash_screen_progress_bar.setProgress(0)
            CoroutineScope(Dispatchers.IO).launch {
                checkLogin()
            }
//            CoroutineScope(Dispatchers.IO).launch {
//                checkLoginCustomer()
//            }
            CoroutineScope(Dispatchers.IO).launch {
                updateData()
            }

            launchLoginActivity()

        }).start()
    }

//    private fun checkLoginCustomer() {
//        val sharedPref = getSharedPreferences("UsernamePasswordPrefCustomer", MODE_PRIVATE)
//        var username = sharedPref.getString("username","").toString()
//        var password = sharedPref.getString("password","").toString()
//
//        CoroutineScope(Dispatchers.IO).launch {
//            if(username== null && password ==null){
//                withContext(Main){
//                    println("null")
//                    startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
//                    finish()
//                }
//            }else{
//                try {
//                    val repository= CustomerRepository()
//                    val response = repository.login(username.toString(),password.toString())
//                    if (response.message == "login successfull"){
//                        MyServiceBuilder.token = "Bearer ${response.token}"
////                        ServiceBuilder.customerId = response.customerID
////                        ServiceBuilder.customerName = response.customerName
////                        ServiceBuilder.customerEmail = response.customerEmail
////                        ServiceBuilder.customerPhone = response.customerPhone
////                        ServiceBuilder.username = response.username
//                        startActivity(
//                            Intent(
//                                this@SplashActivity,
//                                DashboardActivity::class.java
//                            )
//                        )
//                        finish()
//                    }
//                }catch (ex : IOException){
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(
//                            this@SplashActivity,
//                            ex.toString(),
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//            }
//        }
//    }

    private fun updateData() {
        val sharedPref = getSharedPreferences("SaveDetailsPref", MODE_PRIVATE)
        val email = sharedPref.getString("sendemail" , "")
        val phone = sharedPref.getString("sendphone" , "")
        println("after refresh $email , $phone")
//        ServiceBuilder.sendemail = email
//        ServiceBuilder.sendphone = phone


    }

    override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

}