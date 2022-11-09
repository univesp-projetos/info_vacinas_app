package com.herokuapp.infovacinas

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

        // incluindo um listener para o botão
        // e enviado o valor do CEP recebido
        val bundle = Bundle()
        val cepDigitado = "13140-113"
        bundle.putString(/* key = */ "cepDigitado", /* value = */ cepDigitado)

        binding.buttonConsultaPorCepSubmit.setOnClickListener {
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