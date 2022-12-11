package com.example.pizzeria.clases;

import com.example.pizzeria.enums.Tamanno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pizza implements Serializable {

    private Float PRECIO_POR_SALSA = 0.5F;
    private Float PRECIO_POR_INGREDIENTE = 1F;
    private List<String> ingredientes;
    private List<String> salsas;
    private Tamanno tamanno;

    public Pizza() {
        this.ingredientes = new ArrayList();
        this.salsas = new ArrayList();
        this.tamanno = null;
    }

    public Pizza(List<String> ingredientes, List<String> salsas) {
        this.ingredientes = ingredientes;
        this.salsas = salsas;
        this.tamanno = null;
    }

    public Pizza(List<String> ingredientes, Tamanno tamanno) {
        this.ingredientes = ingredientes;
        this.salsas = new ArrayList();
        this.tamanno = tamanno;
    }

    public Pizza(List<String> ingredientes, List<String> salsas, Tamanno tamanno) {
        this.ingredientes = ingredientes;
        this.salsas = salsas;
        this.tamanno = tamanno;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<String> getSalsas() {
        return salsas;
    }

    public void setSalsas(List<String> salsas) {
        this.salsas = salsas;
    }

    public Tamanno getTamanno() {
        return tamanno;
    }

    public void setTamanno(Tamanno tamanno) {
        this.tamanno = tamanno;
    }

    public Float calcularPrecio() {
        return tamanno.getPrecioBase() + (ingredientes.size() * PRECIO_POR_INGREDIENTE) + (salsas.size() * PRECIO_POR_SALSA);
    }

    @Override
    public String toString() {
        return "Pizza:\n\t\tingredientes:\n\t\t\t\t" + ingredientes.toString().replace("[", "").replace("]", "") + "\n\t\tsalsas:\n\t\t\t\t" + salsas.toString().replace("[", "").replace("]", "") + "\n\t\ttamaño:\n\t\t\t\t" + tamanno.toString().replace("nn", "ñ") + "\n\t\tprecio:\n\t\t\t\t" + calcularPrecio() + "€";
    }
}
