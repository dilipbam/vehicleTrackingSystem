package com.dilip.deliverytruckbooking_dilipbam.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dilip.deliverytruckbooking_dilipbam.R
import com.dilip.deliverytruckbooking_dilipbam.model.ListBookingHistoryOwner

class BookingHistoryOwnerAdapter(
    val lstOrderHistoryAdmin: ArrayList<ListBookingHistoryOwner>,
    val context: Context,
) : RecyclerView.Adapter<BookingHistoryOwnerAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val username : TextView
        val vName : TextView
        val vNumber: TextView
        val dliscense : TextView
        val driverName : TextView
        val capacity : TextView
        val rate : TextView
        val type : TextView
        val pick : TextView
        val drop : TextView
        val contact : TextView
        val date : TextView

        init {
            username = view.findViewById(R.id.uname)
            vName = view.findViewById(R.id.vname)
            vNumber = view.findViewById(R.id.vNumber)
            dliscense = view.findViewById(R.id.dlicense)
            driverName = view.findViewById(R.id.dname)
            capacity = view.findViewById(R.id.capacity)
            rate = view.findViewById(R.id.rate)
            type = view.findViewById(R.id.type)
            pick = view.findViewById(R.id.pick)
            drop = view.findViewById(R.id.drop)
            contact = view.findViewById(R.id.ucontact)
            date = view.findViewById(R.id.date)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingHistoryOwnerAdapter.ItemViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_booking_history_owner, parent, false)
        return ItemViewHolder(v)
    }
    //
//    fun deleteItem(pos : Int) {
//        lstOrderHistoryAdmin.removeAt(pos)
//        notifyItemRemoved(pos)
//    }
    override fun onBindViewHolder(holder: BookingHistoryOwnerAdapter.ItemViewHolder, position: Int) {
        var item = lstOrderHistoryAdmin[position]
        holder.username.text = item.Username.toString()
        holder.vName.text = item.VehicleName.toString()
//        var price = (item.Price.toString().toInt()) * ServiceBuilder.updatedQuantity!!
        holder.vNumber.text = item.VehicleNumber.toString()
        holder.dliscense.text = item.DrivingLicense.toString()
        holder.driverName.text = item.DrivingName.toString()
        holder.capacity.text = item.Capacity.toString()
        holder.rate.text = item.Rate.toString()
        holder.type.text = item.Type.toString()
        holder.pick.text = item.PickLocation.toString()
        holder.drop.text = item.DeliverLocation.toString()
        holder.contact.text = item.Phone.toString()
        holder.date.text = item.Date.toString()


    }
    override fun getItemCount(): Int {
        return lstOrderHistoryAdmin.size
    }
}