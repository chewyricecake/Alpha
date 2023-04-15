package com.example.alpha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alpha.Adapter.ParkingAdapter
import com.example.alpha.Extras.Events
import com.example.alpha.Model.Parking
import com.example.alpha.ViewModel.ParkingViewModel
import com.example.alpha.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var binding: ActivityMainBinding

    val parkingViewModel: ParkingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(ExploreFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId){
                R.id.explore ->{
                    loadFragment(ExploreFragment())
                    true
                }
                R.id.go ->{
                    loadFragment(GoFragment())
                    true
                }
                R.id.saved ->{
                    loadFragment(SavedFragment())
                    true
                }
                R.id.pay ->{
                    loadFragment(PayFragment())
                    true
                }
                R.id.updates ->{
                    loadFragment(UdpateFragment())
                    true
                }
                else ->
                    false
            }
        }


        parkingViewModel.get()
        parkingViewModel.parking.observe(this) {
           /* when (it){
                is Events.Loading -> {
                    Log.v(TAG,"LOADING")
                    binding.progressbar.visibility = View.VISIBLE
                }
                is Events.Success -> {
                    Log.v(TAG,"SUCCESS")
                    binding.progressbar.visibility = View.GONE

                    it.response?.let{ response
                        -> getAdapter(response)
                    }

                }
                is Events.Error -> {
                    Log.v(TAG,"ERROR")
                    binding.progressbar.visibility = View.GONE

                    it.let {
                        binding.message.text = it.msg.toString()
                    }
                }
            }*/
        }
    }


    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()
    }

/*    fun getAdapter(list: List<Parking>) = binding.recyclerView.apply{
        layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = ParkingAdapter(list)
    }*/

}
