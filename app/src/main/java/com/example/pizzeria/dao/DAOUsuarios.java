package com.example.pizzeria.dao;

import com.example.pizzeria.clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DAOUsuarios {

    private static DAOUsuarios instance = null;
    private List<Usuario> usuarios;

    private DAOUsuarios() {
        usuarios = new ArrayList();
        usuarios.add(new Usuario("Adrian_oliva", "1234Abcd"));
    }

    public static DAOUsuarios getInstance() {
        if (instance == null) instance = new DAOUsuarios();

        return instance;
    }

    public void resetearUsuarios() {
        usuarios = new ArrayList();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario getUsuario(Usuario usuario) {
        Usuario usuarioDevuelto = null;

        for (Usuario usuarioAct : usuarios) if (usuarioAct.equals(usuario)) usuarioDevuelto = usuarioAct;

        return usuarioDevuelto;
    }

    public Boolean insertarUsuario(Usuario usuario) {
        Boolean insertado = true;

        if (usuarios.contains(usuario)) insertado = false;
        else usuarios.add(usuario);

        return insertado;
    }

}
