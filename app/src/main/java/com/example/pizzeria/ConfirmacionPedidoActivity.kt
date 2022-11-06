package com.example.pizzeria

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.pizzeria.clases.Pizza

class ConfirmacionPedidoActivity : Activity() {

    private lateinit var pizza: Pizza

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacion_pedido)
        pizza = intent.getSerializableExtra("pizza") as Pizza
        val lblPedido: TextView = findViewById(R.id.lblPedido)
        lblPedido.text = pizza.toString()
    }

    override fun onBackPressed() {}
}