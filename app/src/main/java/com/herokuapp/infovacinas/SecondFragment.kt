package com.herokuapp.infovacinas

import android.R
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationServices.*
import com.herokuapp.infovacinas.databinding.FragmentSecondBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // incluindo uma ação para o botão "Consultar" após digitar o CEP
        binding.buttonConsultaPorCepSubmit.setOnClickListener {
            // e enviado o valor do CEP digitado
            val bundle = Bundle()
            var cepDigitado = "13140-113"
            cepDigitado = binding.editTextTextPostalAddress.text.toString()
            bundle.putString(/* key = */ "cepDigitado", /* value = */ cepDigitado)
            findNavController().navigate(
                com.herokuapp.infovacinas.R.id.action_SecondFragment_to_ThirdFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}