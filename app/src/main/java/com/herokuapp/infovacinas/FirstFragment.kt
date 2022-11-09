package com.herokuapp.infovacinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.herokuapp.infovacinas.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ação do botão da tela inicial - Para o SecondFragment - consulta por CEP
        binding.buttonConsultaPorCep.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        // ação do botão da tela inicial - para o FourthFragment -
        binding.buttonConsultaPelaLocalizacao.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_FourthFragment)
        }

        // ação do botão da tela inicial - para o FifthFragment - Quem Somos
        binding.buttonQuemSomos.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_FifthFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}