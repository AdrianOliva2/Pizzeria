package com.example.pizzeria

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonWriter
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pizzeria.clases.Pizza
import com.google.gson.Gson
import org.json.JSONStringer
import java.io.StringWriter
import java.io.Writer
import java.time.Duration

class ConfirmacionPedidoActivity : Activity(), View.OnClickListener {

    private var intExtra: Int = -1
    private lateinit var layout: ConstraintLayout
    private lateinit var pizza: Pizza

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacion_pedido)
        layout = findViewById(R.id.ctrLayout5)
        intExtra = intent.getIntExtra("colorFondo", -1)
        if (intExtra != -1) {
            layout.setBackgroundColor(intExtra)
        }
        pizza = intent.getSerializableExtra("pizza") as Pizza
        val lblPedido: TextView = findViewById(R.id.lblPedido)
        val btnConfirmarPedido: Button = findViewById(R.id.btnConfirmarPedido)
        btnConfirmarPedido.setOnClickListener(this)
        lblPedido.text = pizza.toString()
    }

    override fun onBackPressed() {}

    override fun onClick(p0: View?) {
        val toast: Toast = Toast.makeText(this, "Has comprado una pizza por " + pizza.calcularPrecio() + "€", Toast.LENGTH_LONG)
        toast.show()
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("ultimaPizza", Gson().toJson(pizza, Pizza::class.java))
            apply()
        }
        val intent = Intent(this, LoggedInActivity::class.java)
        if (intExtra != -1) intent.putExtra("colorFondo", (layout.background as ColorDrawable).color)
        startActivity(intent)
        finish()
    }
}