package com.example.alpha.Model

import android.location.Geocoder
import com.google.firebase.firestore.GeoPoint

data class Parking(
    val title: String ="",
    val location: GeoPoint = GeoPoint(0.0,0.0),
    val available: Boolean = true,
)