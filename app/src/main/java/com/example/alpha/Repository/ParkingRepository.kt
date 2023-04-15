package com.example.alpha.Repository

import com.example.alpha.Model.Parking
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ParkingRepository @Inject constructor(var firebaseFirestore: FirebaseFirestore) {

    fun getParking(): Flow<List<Parking>> = flow{
        val data = firebaseFirestore.collection("parking").get().await()
        emit(data.toObjects(Parking::class.java))
    }.flowOn(Dispatchers.Main)

}