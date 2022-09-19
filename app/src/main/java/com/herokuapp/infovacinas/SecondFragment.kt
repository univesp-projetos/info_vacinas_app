package com.herokuapp.infovacinas

import android.R
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.herokuapp.infovacinas.databinding.FragmentSecondBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var latitude: TextView? = null
    private var longitude: TextView? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        latitude = _binding.latitude
        longitude = _binding.longitude
        val button = _binding.btnBuscarPorCoordenada

        button.setOnClickListener() {
            getLocation()
        }

        return binding.root

    }

    private fun getLocation()
    {
        //check location permission
        if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) &&
                (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100)
                return
        }

        // pega a latitude e a longitude
        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if(it!=null) {
                var textLatitude = "Latitude: "+it.latitude.toString()
                var textLongitude = "Longitude: "+it.longitude.toString()
                latitude.text = textLatitude
                longitude.text = textLongitude
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}