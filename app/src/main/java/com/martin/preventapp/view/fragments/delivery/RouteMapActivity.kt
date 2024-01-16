package com.martin.preventapp.view.fragments.delivery

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.model.RoundCap
import com.martin.preventapp.R
import com.martin.preventapp.controller.delivery.interfaces.OrdersToDeliverInterface
import com.martin.preventapp.controller.delivery.orders.OrdersToDeliverController
import com.martin.preventapp.databinding.ActivityRouteMapBinding
import com.martin.preventapp.model.entities.Request.CoordinatesRequest
import com.martin.preventapp.model.entities.Response.RouteResponse

class RouteMapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
GoogleMap.OnMyLocationClickListener, OrdersToDeliverInterface.ViewMap {

    private lateinit var binding: ActivityRouteMapBinding
    private lateinit var map: GoogleMap
    private lateinit var currentLocation: LatLng
    var poly: Polyline? = null

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRouteMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        OrdersToDeliverController.instance!!.setView(this)


        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.backButton.setOnClickListener {
            OrdersToDeliverController.instance!!.setItemsToRoute(null)
            finish()
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        enableLocation()
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
    }

    private fun createRoute(){
        val polylineOptions = PolylineOptions()
            .add(currentLocation)
        var listCoordinates : MutableList<List<Double>> = mutableListOf()
        listCoordinates.add(listOf(currentLocation.longitude, currentLocation.latitude))

        OrdersToDeliverController.instance!!.getItemsToRoute()?.forEach {
            val latlng = LatLng(it.client.lat.toDouble(), it.client.long.toDouble())
            polylineOptions.add(latlng)
            map.addMarker(
                MarkerOptions()
                    .position(latlng)
                    .title(it.client.name)
            )
            listCoordinates.add(listOf(it.client.long.toDouble(), it.client.lat.toDouble()))
        }

        OrdersToDeliverController.instance!!.getRoute(CoordinatesRequest(
                listCoordinates
            )
        )

        /*polylineOptions.width(15f).color(ContextCompat.getColor(this, R.color.border))

        val polyline = map.addPolyline(polylineOptions)
        polyline.startCap = RoundCap()
        polyline.endCap = RoundCap()*/
    }

    override fun showRoute(route: RouteResponse) {
        val polyLineOptions = PolylineOptions()
        route.features?.first()?.geometry?.coordinates?.forEach {
            polyLineOptions.add(LatLng(it[1], it[0]))
        }
        polyLineOptions.width(15f).color(ContextCompat.getColor(this, R.color.border))

        runOnUiThread {
            poly = map.addPolyline(polyLineOptions)
        }
    }

    private fun enableLocation() {
        if (!::map.isInitialized) return
        if (isPermissionsGranted()) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }

            map.isMyLocationEnabled = true

            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    map.clear()
                    currentLocation = LatLng(location.latitude, location.longitude)
                    createMarker()
                    createRoute()
                }
            }

        } else {
            requestLocationPermission()
        }
    }

    private fun createMarker() {
        map.addMarker(MarkerOptions().position(currentLocation).title(""))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 14f),
            1500,
            null)
    }

    private fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                map.isMyLocationEnabled = true
            }else{
                Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Boton pulsado", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "Estás en ${p0.latitude}, ${p0.longitude}", Toast.LENGTH_SHORT).show()
    }
}