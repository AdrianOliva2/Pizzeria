package com.example.pizzeria.clases

import android.os.Build
import java.io.Serializable
import java.util.Base64

class Usuario (private var nombreUsuario: String, contrasenna: String): Serializable {

    private var contrasenna: String

    init {
        this.contrasenna = cifrar(contrasenna)
    }

    private fun cifrar(contrasenna: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Base64.getEncoder().encodeToString(contrasenna.toByteArray(Charsets.UTF_8))
                } else {
                    contrasenna
                }
    }

    fun getNombreUsuario(): String {
        return this.nombreUsuario
    }

    fun setNombreUsuario(nombreUsuario: String) {
        this.nombreUsuario = nombreUsuario
    }

    fun getContrasenna(): String {
        return this.contrasenna
    }

    fun setContrasenna(contrasenna: String) {
        this.contrasenna = cifrar(contrasenna)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (nombreUsuario != other.nombreUsuario) return false

        return true
    }

    override fun hashCode(): Int {
        return nombreUsuario.hashCode()
    }

    override fun toString(): String {
        return "Usuario(nombreUsuario='$nombreUsuario', contrasenna='$contrasenna')"
    }

}