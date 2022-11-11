package com.herokuapp.infovacinas

import android.R
import android.os.Bundle
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
    var arrayList_details:ArrayList<Model> = ArrayList();
    //OkHttpClient creates connection pool between client and server
    val client = OkHttpClient()

    fun run(url: String) {
        progress.visibility = View.VISIBLE
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                progress.visibility = View.GONE
            }

            override fun onResponse(call: Call, response: Response) {
                var str_response = response.body()!!.string()
                //creating json object
                val json_contact:JSONObject = JSONObject(str_response)
                //creating json array
                var jsonarray_info:JSONArray= json_contact.getJSONArray("info")
                var i:Int = 0
                var size:Int = jsonarray_info.length()
                arrayList_details= ArrayList();
                for (i in 0.. size-1) {
                    var json_objectdetail:JSONObject=jsonarray_info.getJSONObject(i)
                    var model:Model= Model();
                    model.id=json_objectdetail.getString("id")
                    model.name=json_objectdetail.getString("name")
                    model.email=json_objectdetail.getString("email")
                    arrayList_details.add(model)
                }

                requireActivity().runOnUiThread {
                    //stuff that updates ui
                    val obj_adapter : CustomAdapter
                    val applicationContext = requireActivity().application
                    obj_adapter = CustomAdapter(applicationContext,arrayList_details)
                    listView_details.adapter=obj_adapter
                }
                progress.visibility = View.GONE
            }
        })
    }

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

        progress = binding.progressBar

        val View: View = inflater.inflate(R.layout.activity_list_item, container, false)

        progress.visibility = View.VISIBLE
        listView_details = binding.listView as ListView
        run("http://10.0.0.7:8080/jsondata/index.html")

        return inflater.inflate(R.layout.activity_list_item, container, false);

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}