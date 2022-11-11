package com.herokuapp.infovacinas

class ModelUbs {
    lateinit var BAIRRO: String
    lateinit var CEP: String
    var CO_CNES: Int = 0
    var DISTANCIA: Double = 0.0
    var LATITUDE: Double = 0.0
    lateinit var LOGRADOURO: String
    var LONGITUDE: Double = 0.0
    lateinit var MUNICIPIO: String
    lateinit var NOME: String
    lateinit var NUMERO: String
    lateinit var UF: String

    constructor(BAIRRO: String, CEP: String, CO_CNES: Int, DISTANCIA: Double, LATITUDE: Double, LOGRADOURO: String, LONGITUDE: Double, MUNICIPIO: String, NOME: String, NUMERO: String, UF: String) {
        this.BAIRRO = BAIRRO
        this.CEP = CEP
        this.CO_CNES = CO_CNES
        this.DISTANCIA = DISTANCIA
        this.LATITUDE = LATITUDE
        this.LOGRADOURO = LOGRADOURO
        this.LONGITUDE - LONGITUDE
        this.MUNICIPIO = MUNICIPIO
        this.NOME = NOME
        this.NUMERO = NUMERO
        this.UF = UF
    }
    constructor()
}