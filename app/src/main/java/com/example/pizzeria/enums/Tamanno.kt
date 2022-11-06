package com.example.pizzeria.enums

enum class Tamanno(private val precioBase: Float) {
    PEQUENNA(5F), MEDIANA(8F), FAMILIAR(10F);

    fun getPrecioBase(): Float {
        return precioBase
    }
}