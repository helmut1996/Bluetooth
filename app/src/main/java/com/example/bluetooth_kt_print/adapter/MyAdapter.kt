package com.example.bluetooth_kt_print.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bluetooth_kt_print.R
import com.example.bluetooth_kt_print.models.Devices_list

class MyAdapter(private val Deviceslist:ArrayList<Devices_list>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_devices, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = Deviceslist [position]
        holder.name.text =currentItem.nombre
        holder.address.text = currentItem.direccion
    }

    override fun getItemCount(): Int {
        return Deviceslist.size
    }



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val name: TextView= itemView.findViewById(R.id.list_name)
        val address: TextView = itemView.findViewById(R.id.list_address)
    }
}