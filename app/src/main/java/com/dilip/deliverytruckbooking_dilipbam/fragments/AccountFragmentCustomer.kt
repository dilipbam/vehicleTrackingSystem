package com.dilip.deliverytruckbooking_dilipbam.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.dilip.deliverytruckbooking_dilipbam.API.MyServiceBuilder
import com.dilip.deliverytruckbooking_dilipbam.AccUpdateActivity
import com.dilip.deliverytruckbooking_dilipbam.BookingHistoryActivity
import com.dilip.deliverytruckbooking_dilipbam.R
import com.dilip.deliverytruckbooking_dilipbam.ui.CustomerAccountUpdate
import com.dilip.deliverytruckbooking_dilipbam.ui.LoginActivity
import de.hdodenhof.circleimageview.CircleImageView


class AccountFragmentCustomer : Fragment() {
    private lateinit var imgProfile: CircleImageView
    private lateinit var fullName: TextView
    private lateinit var email: TextView
    private lateinit var phone: TextView
    private lateinit var editProfile: Button
    private lateinit var favourites: TextView
    private lateinit var view2: View
    private lateinit var order: TextView
    private lateinit var logout: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account_customer, container, false)
        imgProfile = view.findViewById(R.id.imgProfile)
        fullName = view.findViewById(R.id.fullName)
        email = view.findViewById(R.id.email)
        phone = view.findViewById(R.id.phone)
        editProfile = view.findViewById(R.id.editProfile)
        favourites = view.findViewById(R.id.favourites)
        view2 = view.findViewById(R.id.view2)
        order = view.findViewById(R.id.order)
        logout = view.findViewById(R.id.logout)
        fullName.text = MyServiceBuilder.customerName
        imgProfile.setOnClickListener{

        }
        // showDetails()

        email.text = MyServiceBuilder.customerEmail
        phone.text = MyServiceBuilder.customerPhone

        logout.setOnClickListener {
            logout()
        }
        editProfile.setOnClickListener {
            editProfile()
        }

        order.setOnClickListener {

            startActivity(Intent(context, BookingHistoryActivity::class.java))
        }
        return view

        //commit done of account
    }

    private fun editProfile() {
        startActivity(Intent(context, CustomerAccountUpdate::class.java))
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

}