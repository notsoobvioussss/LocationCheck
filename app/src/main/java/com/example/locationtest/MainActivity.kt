package com.example.locationtest

import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.locationtest.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

lateinit var bindingCLass: ActivityMainBinding
class MainActivity : AppCompatActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        bindingCLass = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingCLass.root)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        bindingCLass.btnShow.setOnClickListener{
            fetchLocation()
        }
    }
    private fun fetchLocation(){
        val task = fusedLocationProviderClient.lastLocation

        if(ActivityCompat.checkSelfPermission(
                this,android.Manifest.permission.ACCESS_FINE_LOCATION
            )!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )!= PackageManager.PERMISSION_GRANTED
                ){
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101
                )
            return
        }
        task.addOnSuccessListener {
            if(it != null){
                Toast.makeText(this, "${it.longitude}  ${it.latitude}", Toast.LENGTH_LONG).show()
                bindingCLass.textView.setText("${it.longitude}  ${it.latitude}")

            }
        }
    }
}