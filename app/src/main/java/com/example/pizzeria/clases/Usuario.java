package com.example.pizzeria.clases;

import android.os.Build;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class Usuario implements Serializable {

    private String nombreUsuario;
    private String contrasenna;

    public Usuario(String nomUsuario, String contrasenya) {
        this.nombreUsuario = nomUsuario;
        this.contrasenna = contrasenya;
    }

    private String cifrar(String contrasenna) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Base64.getEncoder().encodeToString(contrasenna.getBytes(StandardCharsets.UTF_8));
        } else {
            return contrasenna;
        }
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = cifrar(contrasenna);
    }

    public String getContrasenna() {
        return this.contrasenna;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return nombreUsuario.equals(usuario.nombreUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario);
    }

    @Override
    public String toString() {
        return "Usuario(nombreUsuario='" + nombreUsuario + "', contrasenna='" + contrasenna + "')";
    }
}
