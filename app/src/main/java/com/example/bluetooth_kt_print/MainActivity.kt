package com.example.bluetooth_kt_print

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluetooth_kt_print.adapter.MyAdapter
import com.example.bluetooth_kt_print.databinding.ActivityMainBinding
import com.example.bluetooth_kt_print.models.Devices_list

lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateinit var adapter: MyAdapter
        lateinit var DevicesArrays:ArrayList<Devices_list>
        lateinit var m_pairedDevices:Set<BluetoothDevice>
        val   bluetoothManager=applicationContext.getSystemService(AppCompatActivity.BLUETOOTH_SERVICE)as BluetoothManager
        val bluetoothAdapter=bluetoothManager.adapter
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager= layoutManager
        binding.recyclerView.setHasFixedSize(true)

        binding.btnOn.setOnClickListener {
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent,1)
            Toast.makeText(this,"Bluetooh Encendido", Toast.LENGTH_LONG).show()
        }


        binding.btnOff.setOnClickListener {

            bluetoothAdapter.disable()
            Toast.makeText(this,"Bluetooh Apagado", Toast.LENGTH_LONG).show()
        }



        binding.btnDevices.setOnClickListener {
            var pairedDevices = bluetoothAdapter.bondedDevices
            var data:StringBuffer=StringBuffer()

            for (device:BluetoothDevice in pairedDevices){
                data.append("Device Name="+device.name+"Devices Address"+device.address)

                DevicesArrays = arrayListOf<Devices_list>()

                val Dev = Devices_list(device.name,device.address)
                DevicesArrays.add(Dev)
            }
            if (data.isEmpty()){
                Toast.makeText(this,"Bluetooh Devices not Paired", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,data, Toast.LENGTH_LONG).show()

                adapter = MyAdapter(DevicesArrays)
                binding.recyclerView.adapter = adapter
            }
        }


      /*

        binding.recyclerView.setOnClickListener {
            PairedDeviceList()
        }

       */
    }






}