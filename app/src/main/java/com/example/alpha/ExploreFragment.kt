package com.example.alpha

import android.app.Activity
import android.location.*
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alpha.Extras.Events
import com.example.alpha.Model.Parking
import com.example.alpha.ViewModel.ParkingViewModel
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
//OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener

class ExploreFragment : Fragment() {

    private val TAG = "ExploreFragment"
    lateinit var googleMap: GoogleMap
    private var mapReady = false
    lateinit var parkingViewModel: ParkingViewModel
    lateinit var parkings: List<Parking>
    /*var googleApiClient: GoogleApiClient? = null
    lateinit var locationRequest: LocationRequest*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_explore, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync{
            it -> googleMap = it
            mapReady = true
            centerOnMyLocation()
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let{
            parkingViewModel = ViewModelProvider(it!!).get(ParkingViewModel::class.java)
        }
        parkingViewModel.parking.observe(viewLifecycleOwner, Observer {
            it ->
             when (it){
                is Events.Loading -> {
                    Log.v(TAG,"LOADING")
                }
                is Events.Success -> {
                    Log.v(TAG,"SUCCESS")
                    it.response?.let{ response->
                        this.parkings = response
                        updateMap()
                    }
                }
                is Events.Error -> {
                    Log.v(TAG,"ERROR")
                }
            }

        })
    }

    private fun updateMap(){
        if (mapReady && parkings != null){
            parkings.forEach{ parking->
                val marker = LatLng(parking.location.latitude, parking.location.longitude)
                googleMap.addMarker(MarkerOptions().position(marker).title(parking.title.plus(": ").plus(parking.available)))
            }
        }
    }



    private fun centerOnMyLocation(){

        //Physical Device
        /*googleMap.isMyLocationEnabled = true
        var location = googleMap.myLocation*/

        //Emulator does not have a built in GPS value
        var location = LatLng(40.2743 ,-75.3853)

        if(location != null){
            val marker = LatLng(location.latitude, location.longitude)
            googleMap.addMarker(MarkerOptions().position(marker).title("Current Location"))
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,15f))
    }

/*    protected fun buildGoogleApiClient(){
        googleApiClient = GoogleApiClient.Builder(requireContext().applicationContext)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API).build()
        googleApiClient!!.connect()
    }


    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }

    override fun onLocationChanged(location: Location) {
        TODO("Not yet implemented")
    }

    override fun onConnected(p0: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }


    fun searchLocation(view: View){
        val locationSearch: EditText = findViewById(R.id.et_search)
        var location: String
        location = locationSearch.text.toString().trim()
        var addressList: List<Address>? = null

        if (location == null || location == ""){
            Toast.makeText(this, "provide location", Toast.LENGTH_SHORT).show()
        }else{
            val geoCoder = Geocoder(this)
            try {
                addressList = geoCoder.getFromLocationName(location, 1)
            }catch (e: IOException){
                e.printStackTrace()
            }

            val address = addressList!![0]
            val latLng = LatLng(address.latitude, address.longitude)
            mMap!!.addMarker(MarkerOptions().position(latLng).title(location))
            mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        }
    }*/

}