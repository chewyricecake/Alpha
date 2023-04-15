package com.example.alpha.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alpha.Extras.Events
import com.example.alpha.Model.Parking
import com.example.alpha.Repository.ParkingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParkingViewModel @Inject constructor(val parkingRepository: ParkingRepository): ViewModel() {
    val TAG = "ParkingViewModel"

    val parking: MutableLiveData<Events<List<Parking>>> = MutableLiveData()

    fun get(){
        viewModelScope.launch {
            parkingRepository.getParking().catch {
                Log.e(TAG, "${it.localizedMessage}")
                parking.postValue(Events.Error(msg = it.localizedMessage))
            }.collect{
                parking.postValue(Events.Success(it))
            }
        }
    }
}