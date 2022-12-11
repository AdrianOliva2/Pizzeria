package com.example.pizzeria.clases

class Item {

    private var txtPizza: String

    constructor(txtPizza: String) {
        this.txtPizza = txtPizza
    }

    fun getTxtPizza(): String{
        return this.txtPizza
    }

    fun setTxtPizza(txtPizza: String){
        this.txtPizza = txtPizza
    }

}