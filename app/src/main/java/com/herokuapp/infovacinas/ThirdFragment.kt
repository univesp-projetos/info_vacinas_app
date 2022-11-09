package com.herokuapp.infovacinas

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.herokuapp.infovacinas.databinding.FragmentThirdBinding


/**
 * A simple [Fragment] subclass as the third destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)

        // recebe o valor cepDigitado do fragmento anterior via parameter
        var cepDigitado = arguments?.getString("cepDigitado")
        // escreve o CEP recebido no fragmento atual
        binding.textViewCepDigitado.text = cepDigitado
        // também seta o valor do CEP na variável da MainActivity, se necessário
        (activity as MainActivity).cep = cepDigitado.toString()

        // monta a url por CEP
        var urlApi = (activity as MainActivity).urlApiBase.toString().plus("consulta_por_cep?cep=").plus(cepDigitado)
        binding.urlApiCep.text = urlApi

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}