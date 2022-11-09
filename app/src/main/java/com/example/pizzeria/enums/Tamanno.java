package com.example.pizzeria.enums;

public enum Tamanno {
    PEQUENNA(5f), MEDIANA(8f), FAMILIAR(10f);

    private Float precioBase;

    Tamanno(Float precioBase) { this.precioBase = precioBase; }

    public Float getPrecioBase() {
        return precioBase;
    }
}
