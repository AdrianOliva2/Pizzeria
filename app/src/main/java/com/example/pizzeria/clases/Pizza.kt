package com.example.pizzeria.clases

import com.example.pizzeria.enums.Tamanno
import java.io.Serializable

class Pizza: Serializable {

    private lateinit var ingredientes: List<String>
    private lateinit var salsas: List<String>
    private var tamanno: Tamanno?

    constructor() {
        this.ingredientes = ArrayList()
        this.salsas = ArrayList()
        this.tamanno = null
    }

    constructor(ingredientes: List<String>, salsas: List<String>) {
        this.ingredientes = ingredientes
        this.salsas = salsas
        this.tamanno = null
    }

    constructor(ingredientes: List<String>, salsas: List<String>, tamanno: Tamanno) {
        this.ingredientes = ingredientes
        this.salsas = salsas
        this.tamanno = tamanno
    }

    fun getIngredientes(): List<String> {
        return ingredientes
    }

    fun setIngredientes(ingredientes: List<String>) {
        this.ingredientes = ingredientes
    }

    fun getSalsas(): List<String> {
        return salsas
    }

    fun setSalsas(salsas: List<String>) {
        this.salsas = salsas
    }

    fun getTamanno(): Tamanno? {
        return tamanno
    }

    fun setTamanno(tamanno: Tamanno) {
        this.tamanno = tamanno
    }

    override fun toString(): String {
        return "Pizza:\n\tingredientes:\n\t\t${ingredientes.toString().replace("[", "").replace("]", "")}\n\tsalsas:\n\t\t${salsas.toString().replace("[", "").replace("]", "")}\n\ttamanno:\n\t\t$tamanno"
    }

}