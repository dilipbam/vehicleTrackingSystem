package com.dilip.deliverytruckbooking_dilipbam

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dilip.deliverytruckbooking_dilipbam.entity.Customer
import com.dilip.deliverytruckbooking_dilipbam.repository.CustomerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class CustomerSignUp : Fragment() {
    private lateinit var etFname : EditText
    private lateinit var etMname : EditText
    private lateinit var etLname : EditText
    private lateinit var etuname : EditText
    private lateinit var etPassword : EditText
    private lateinit var etEmail : EditText
    private lateinit var etContact : EditText
    private lateinit var btnSignUp: Button



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val view = inflater.inflate(R.layout.fragment_cusotmer_sign_up, container, false)
        etFname = view.findViewById(R.id.etFname)
        etMname = view.findViewById(R.id.etMname)
        etLname = view.findViewById(R.id.etLname)
        etuname = view.findViewById(R.id.etuname)
        etPassword = view.findViewById(R.id.etPassword)
        etEmail = view.findViewById(R.id.etEmail)
        etContact = view.findViewById(R.id.etContact)
        btnSignUp = view.findViewById(R.id.btnSignup)

        btnSignUp.setOnClickListener{
            registerUser()
        }
        return view
    }

    private fun registerUser(){
        if (checkEmpty()){
            val firstName = etFname.text.toString()
            val middleName = etMname.text.toString()
            val lastName = etLname.text.toString()
            val userName = etuname.text.toString()
            val password = etPassword.text.toString()
            val email = etEmail.text.toString()
            val contact = etContact.text.toString()

            val customer = Customer(
                    firstName = firstName,
                    middleName = middleName,
                    lastName = lastName,
                    userName = userName,
                    password = password,
                    email = email,
                    contact = contact
            )// parameters provided to Customer

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val customerRepository = CustomerRepository()
                    val response = customerRepository.registerCustomer(customer)
                    if (response.message == "Successfully Registered"){
                        withContext(Main){
                            Toast.makeText(
                                    context,
                                    "response.message",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }catch (ex: Exception){
                    withContext(Main){
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

    private fun checkEmpty(): Boolean{
        var flag=true
        when{
            TextUtils.isEmpty(etFname.text)->{
                etFname.error="Please enter your first name"
                etFname.requestFocus()
                flag = false
            }
            TextUtils.isEmpty(etMname.text)->{
                etMname.error="Please enter your middle name"
                etMname.requestFocus()
                flag = false
            }
            TextUtils.isEmpty(etLname.text)->{
                etLname.error="please enter your last name"
                etLname.requestFocus()
                flag=false
            }
            TextUtils.isEmpty(etuname.text)->{
                etuname.error="Please enter your username"
                etuname.requestFocus()
                flag= false
            }
            TextUtils.isEmpty(etPassword.text)->{
                etPassword.error="Please enter your password"
                etPassword.requestFocus()
                flag = false
            }
            TextUtils.isEmpty(etEmail.text)->{
                etEmail.error="Please enter your email address"
                etEmail.requestFocus()
                flag = false
            }
            TextUtils.isEmpty(etContact.text)->{
                etContact.error="Please enter your contact number"
                etContact.requestFocus()
                flag = false
            }
        }
        return flag
    }
}