package com.dilip.deliverytruckbooking_dilipbam.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dilip.deliverytruckbooking_dilipbam.BookingActivity
import com.dilip.deliverytruckbooking_dilipbam.R
import com.dilip.deliverytruckbooking_dilipbam.model.ListVehicle

class recyclerViewVehicleAdapterCustomer(
    val lstVehicle: ArrayList<ListVehicle>,
    val context: Context
) : RecyclerView.Adapter<recyclerViewVehicleAdapterCustomer.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val vName : TextView
        val vNumber: TextView
        val dliscense : TextView
        val driverName : TextView
        val capacity : TextView
        val rate : TextView
        val type : TextView
        val clickToBook : TextView
        init {
            vName = view.findViewById(R.id.vName)
            vNumber = view.findViewById(R.id.vNumber)
            dliscense = view.findViewById(R.id.dliscence)
            driverName = view.findViewById(R.id.driverName)
            capacity = view.findViewById(R.id.capacity)
            rate = view.findViewById(R.id.rate)
            type = view.findViewById(R.id.type)
            clickToBook = view.findViewById(R.id.clickToBook)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vehicle_details, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//        var finalprice : Int? = 0
        var item = lstVehicle[position]
        println("item $item")

        holder.vName.text = item.VehicleName.toString()
//        var price = (item.Price.toString().toInt()) * ServiceBuilder.updatedQuantity!!
        holder.vNumber.text = item.VehicleNumber.toString()
        holder.dliscense.text = item.DrivingLicense.toString()
        holder.driverName.text = item.DrivingName.toString()
        holder.capacity.text = item.Capacity.toString()
        holder.rate.text = item.Rate.toString()
        holder.type.text = item.Radio.toString()
//        holder.tvQuantity.text = "Quantity ${item.foodQuantity.toString().toInt()}"
//        {
//            println("Price is Null")
//        }

        holder.clickToBook.setOnClickListener() {

            val intent = Intent(context, BookingActivity::class.java)
            intent.putExtra("name","${item.VehicleName}")
            intent.putExtra("number","${item.VehicleNumber}")
            intent.putExtra("dliscense","${item.DrivingLicense}")
            intent.putExtra("dname","${item.DrivingName}")
            intent.putExtra("capacity","${item.Capacity}")
            intent.putExtra("rate","${item.Rate}")
            intent.putExtra("type","${item.Radio}")
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return lstVehicle.size
    }
}