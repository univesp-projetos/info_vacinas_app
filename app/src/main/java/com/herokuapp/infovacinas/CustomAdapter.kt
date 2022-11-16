package com.herokuapp.infovacinas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView

class CustomAdapter(context: Context,arrayListDetails:ArrayList<ModelUbs>) : BaseAdapter() {
    private val layoutInflater: LayoutInflater
    private val arrayListDetails:ArrayList<ModelUbs>

    init {
        this.layoutInflater = LayoutInflater.from(context)
        this.arrayListDetails=arrayListDetails
    }

    override fun getCount(): Int {
        return arrayListDetails.size
    }

    override fun getItem(position: Int): Any {
        return arrayListDetails.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val listRowHolder: ListRowHolder
        if (convertView == null) {
            view = this.layoutInflater.inflate(R.layout.adapter_layout, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }

        listRowHolder.tvNome.text = arrayListDetails.get(position).NOME.toString()
        listRowHolder.tvEndereco.text = arrayListDetails.get(position).LOGRADOURO.toString().plus(", ").plus(arrayListDetails.get(position).NUMERO.toString())
        listRowHolder.tvBairro.text = arrayListDetails.get(position).BAIRRO.toString()
        listRowHolder.tvMunicipio.text = arrayListDetails.get(position).MUNICIPIO.toString()

        return view
    }
}

    private class ListRowHolder(row: View?) {
        public val tvNome: TextView
        public val tvEndereco: TextView
        public val tvBairro: TextView
        public val tvMunicipio: TextView
        public val linearLayout: LinearLayout

    init {
        this.tvNome = row?.findViewById<TextView>(R.id.tvNome) as TextView
        this.tvEndereco = row?.findViewById<TextView>(R.id.tvEndereco) as TextView
        this.tvBairro = row?.findViewById<TextView>(R.id.tvBairro) as TextView
        this.tvMunicipio = row?.findViewById<TextView>(R.id.tvMunicipio) as TextView
        this.linearLayout = row?.findViewById<LinearLayout>(R.id.linearLayout) as LinearLayout
    }
}