package com.example.pizzeria

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pizzeria.clases.Pizza
import com.google.gson.Gson

class ConfirmacionPedidoActivity : PlantillaActivity(), View.OnClickListener {

    private lateinit var pizza: Pizza

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacion_pedido)
        val layout: ConstraintLayout = findViewById(R.id.ctrLayout8)
        if (backgroundColor != -1) {
            layout.setBackgroundColor(backgroundColor)
        } else {
            layout.background = null
        }
        pizza = intent.getSerializableExtra("pizza") as Pizza
        val lblPedido: TextView = findViewById(R.id.lblPedido)
        val btnConfirmarPedido: Button = findViewById(R.id.btnConfirmarPedido)
        btnConfirmarPedido.setOnClickListener(this)
        lblPedido.text = pizza.toString()
    }

    override fun onClick(p0: View?) {
        val toast: Toast = Toast.makeText(this, "Has comprado una pizza por " + pizza.calcularPrecio() + "€", Toast.LENGTH_LONG)
        toast.show()
        val sharedPref = this?.getSharedPreferences("Config", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("ultimaPizza", Gson().toJson(pizza, Pizza::class.java))
            apply()
        }
        startActivity(Intent(this, LoggedInActivity::class.java))
        finish()
    }
}