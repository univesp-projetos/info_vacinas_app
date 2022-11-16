package com.herokuapp.infovacinas

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.herokuapp.infovacinas.databinding.FragmentThirdBinding
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


/**
 * A simple [Fragment] subclass as the third destination in the navigation.
 */
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var progress:ProgressBar
    lateinit var listView_details: ListView
    var arrayList_details:ArrayList<ModelUbs> = ArrayList();
    //OkHttpClient creates connection pool between client and server
    val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)

        // recebe o valor cepDigitado do fragmento anterior via parameter
        var cepDigitado = arguments?.getString("cepDigitado")
        // escreve o CEP recebido no fragmento atual
        //binding.textViewCepDigitado.text = cepDigitado
        // também seta o valor do CEP na variável da MainActivity, se necessário
        (activity as MainActivity).cep = cepDigitado.toString()
        // remove o - do cep
        cepDigitado = cepDigitado.toString().replace("-", "")
        // monta a url por CEP
        var urlApi = (activity as MainActivity).urlApiBase.toString().plus("consulta_por_cep?cep=").plus(cepDigitado)

        // seta as variáveis da barra de progresso e da listagem das UBSs na tela
        progress = binding.progressBar
        // seta a barra como visivel
        progress.visibility = View.VISIBLE
        listView_details = binding.listView

        // acessa a Api para preencher os dados na tela
        //urlApi = "https://raw.githubusercontent.com/royopa/sample.net/master/index.html"
        run(urlApi, binding)

        return binding.root
    }

    fun run(url: String, binding: FragmentThirdBinding) {
        progress = binding.progressBar
        progress.visibility = View.VISIBLE
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // remove a barra de processamento
                progress.visibility = View.GONE
                // exibe mensagem de erro
                //binding.textView2.text = "Erro na consulta à API, tente novamente mais tarde."
            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()
                //creating json object
                val json_contact:JSONObject = JSONObject(str_response)
                //creating json array
                var jsonarray_info:JSONArray= json_contact.getJSONArray("resultado")
                var i:Int = 0
                var size:Int = jsonarray_info.length()
                arrayList_details= ArrayList();
                for (i in 0.. size-1) {
                    var json_objectdetail:JSONObject=jsonarray_info.getJSONObject(i)
                    var model:ModelUbs= ModelUbs();

                    model.BAIRRO = json_objectdetail.getString("BAIRRO")
                    model.CEP = json_objectdetail.getString("CEP")
                    model.CO_CNES = json_objectdetail.getInt("CO_CNES")
                    model.DISTANCIA = json_objectdetail.getDouble("DISTANCIA")
                    model.LATITUDE = json_objectdetail.getDouble("LATITUDE")
                    model.LOGRADOURO = json_objectdetail.getString("LOGRADOURO")
                    model.LONGITUDE = json_objectdetail.getDouble("LONGITUDE")
                    model.MUNICIPIO = json_objectdetail.getString("MUNICIPIO")
                    model.NOME = json_objectdetail.getString("NOME")
                    model.NUMERO = json_objectdetail.getString("NUMERO")
                    model.UF = json_objectdetail.getString("UF")

                    arrayList_details.add(model)
                }

                /*
                Handler(Looper.getMainLooper()).post(Runnable {
                    //stuff that updates ui
                    val obj_adapter : CustomAdapter
                    val applicationContext = requireActivity().application
                    obj_adapter = CustomAdapter(applicationContext,arrayList_details)
                    listView_details = binding.listView
                    listView_details.adapter = obj_adapter
                    progress.visibility = View.GONE
                })
                */

                requireActivity().runOnUiThread {
                    //stuff that updates ui
                    val obj_adapter : CustomAdapter
                    val applicationContext = requireActivity().application
                    obj_adapter = CustomAdapter(applicationContext,arrayList_details)
                    listView_details = binding.listView
                    listView_details.adapter = obj_adapter
                    progress.visibility = View.GONE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}