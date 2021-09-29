package com.dilip.deliverytruckbooking_dilipbam.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dilip.deliverytruckbooking_dilipbam.R
import com.dilip.deliverytruckbooking_dilipbam.entity.Owner

class OwnerAdapter(
    val lstOwner: MutableList<Owner>,
    val context: Context
) : RecyclerView.Adapter<OwnerAdapter.OwnerViewHolder>() {
    class OwnerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtCaption: TextView = view.findViewById(R.id.txtCaption)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.owner_custom_layout, parent, false)
        return OwnerViewHolder(view)
    }

    override fun onBindViewHolder(holder: OwnerViewHolder, position: Int) {
        val owner = lstOwner[position]
        holder.txtCaption.text = owner.UserName
    }

    override fun getItemCount(): Int {
        return lstOwner.size
    }
}