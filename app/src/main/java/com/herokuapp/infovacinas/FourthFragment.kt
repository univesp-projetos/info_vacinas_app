package com.herokuapp.infovacinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.herokuapp.infovacinas.databinding.FragmentFourthBinding
import com.herokuapp.infovacinas.databinding.FragmentSecondBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [FourthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FourthFragment : Fragment() {

    private var _binding: FragmentFourthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFourthBinding.inflate(inflater, container, false)

        // função para incluir a informação de latitude e longitude
        (activity as MainActivity).getLastLocation()
        var latitude = (activity as MainActivity).latitude.toString()
        var longitude = (activity as MainActivity).longitude.toString()

        // coloca os valores na tela
        binding.latTextView.text = latitude.toString()
        binding.lonTextView.text = longitude.toString()

        // monta a url por localização
        var urlBase = (activity as MainActivity).urlApiBase.toString()
        var urlApi = urlBase.plus("consulta_por_localizacao?latitude=").plus(latitude).plus("&longitude=").plus(longitude)
        binding.urlApiLocation.text = urlApi

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}