package com.example.alpha

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alpha.Adapter.ParkingAdapter
import com.example.alpha.Extras.Events
import com.example.alpha.Model.Parking
import com.example.alpha.ViewModel.ParkingViewModel
import com.example.alpha.databinding.FragmentSavedBinding


class SavedFragment : Fragment() {

    lateinit var binding:FragmentSavedBinding

    val TAG = "SavedFragment"
    lateinit var parkingViewModel: ParkingViewModel
    lateinit var parkingAdapter: ParkingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSavedBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false)
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
                    binding.recyclerView.setHasFixedSize(true)
                    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext().applicationContext)
                    parkingAdapter = it.response?.let{
                        response -> ParkingAdapter(response)
                    }!!
                    binding.recyclerView.adapter = parkingAdapter

                }
                is Events.Error -> {
                    Log.v(TAG,"ERROR")
                }
            }

        })
    }


}