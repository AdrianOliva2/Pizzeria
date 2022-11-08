package com.example.pizzeria.clases

import com.example.pizzeria.enums.Tamanno
import java.io.Serializable

class Pizza: Serializable {

    private var PRECIO_POR_SALSA: Float = 0.5F
    private var PRECIO_POR_INGREDIENTE: Float = 1F
    private var ingredientes: List<String>
    private var salsas: List<String>
    private var tamanno: Tamanno?

    constructor() {
        this.ingredientes = ArrayList()
        this.salsas = ArrayList()
        this.tamanno = Tamanno.PEQUENNA
    }

    constructor(ingredientes: List<String>, salsas: List<String>) {
        this.ingredientes = ingredientes
        this.salsas = salsas
        this.tamanno = Tamanno.PEQUENNA
    }

    constructor(ingredientes: List<String>, tamanno: Tamanno?) {
        this.ingredientes = ingredientes
        this.salsas = ArrayList()
        this.tamanno = tamanno
    }

    constructor(ingredientes: List<String>, salsas: List<String>, tamanno: Tamanno?) {
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

    fun setTamanno(tamanno: Tamanno?) {
        this.tamanno = tamanno
    }

    fun calcularPrecio(): Float {
        return tamanno!!.getPrecioBase() + (ingredientes.size * PRECIO_POR_INGREDIENTE) + (salsas.size * PRECIO_POR_SALSA)
    }

    override fun toString(): String {
        return "Pizza:\n\t\tingredientes:\n\t\t\t\t${ingredientes.toString().replace("[", "").replace("]", "")}\n\t\tsalsas:\n\t\t\t\t${salsas.toString().replace("[", "").replace("]", "")}\n\t\ttamaño:\n\t\t\t\t${tamanno.toString().replace("nn", "ñ")}\n\t\tprecio:\n\t\t\t\t${calcularPrecio()}€"
    }

}