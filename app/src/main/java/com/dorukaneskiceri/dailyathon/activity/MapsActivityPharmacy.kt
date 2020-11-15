package com.dorukaneskiceri.dailyathon.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dorukaneskiceri.dailyathon.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivityPharmacy : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_pharmacy)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val pharmacyName = intent.getStringExtra("pharmacyName")
        val pharmacyLocation = intent.getStringExtra("pharmacyLocation")
        val separated = pharmacyLocation?.split(",")
        val latitude = separated!![0].toDouble()
        val longitude = separated[1].toDouble()

        val location = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(location).title(pharmacyName + " Eczanesi"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15.0F))
    }
}