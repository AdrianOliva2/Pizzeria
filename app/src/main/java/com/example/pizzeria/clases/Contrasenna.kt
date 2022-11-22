package com.example.pizzeria.clases

import android.os.Build
import java.util.*

class Contrasenna {

    companion object {
        fun cifrar(texto: String): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Base64.getEncoder().encodeToString(texto.toByteArray(Charsets.UTF_8))
            } else {
                texto
            }
        }
    }

}