package com.kobunhada.android.example.mapviewexample

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kobunhada.android.example.mapviewexample.databinding.FragmentMapsBinding

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapsBinding
    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.apply {
            onCreate(savedInstanceState)
            getMapAsync(this@MapsFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        googleMap?.let { map ->
            map.clear()
            val latLng = LatLng(35.18411, 137.04878)
            map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(latLng, 18f)
            )
            map.addMarker(MarkerOptions().position(latLng))
        }
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap
        if (ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this.requireContext(), "現在地が取得できません", Toast.LENGTH_LONG).show()
        } else {
            googleMap!!.isMyLocationEnabled = true
        }

        googleMap!!.uiSettings.let {
            it.isScrollGesturesEnabled = true
            it.isZoomControlsEnabled = true
            it.isZoomGesturesEnabled = true
            it.isRotateGesturesEnabled = true
            it.isMapToolbarEnabled = true
            it.isMyLocationButtonEnabled = true
            it.isCompassEnabled = true
        }
        MapsInitializer.initialize(requireContext())
        onResume()
    }
}