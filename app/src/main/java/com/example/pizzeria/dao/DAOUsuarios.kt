package com.example.pizzeria.dao

import com.example.pizzeria.clases.Usuario

class DAOUsuarios private constructor(private var usuarios: List<Usuario> = ArrayList()) {

    companion object {
        private var instance: DAOUsuarios? = null
        fun getInstance(): DAOUsuarios? {
            if (instance == null) instance = DAOUsuarios()

            return instance
        }
    }

    init {
        usuarios += Usuario("Adrian_oliva", "1234Abcd")
    }

    fun resetearUsuarios() {
        usuarios = ArrayList()
    }

    fun getUsuarios(): List<Usuario> {
        return usuarios
    }

    fun getUsuario(usuario: Usuario): Usuario? {
        var usuarioDevuelto: Usuario? = null

        for (usuarioAct: Usuario in usuarios) if (usuarioAct.equals(usuario)) usuarioDevuelto = usuarioAct

        return usuarioDevuelto
    }

    fun insertarUsuario(usuario: Usuario): Boolean {
        var insertado = true

        if (usuarios.contains(usuario)) insertado = false
        else usuarios += usuario

        return insertado
    }

}
