package com.example.pizzeria

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pizzeria.clases.Pizza
import com.example.pizzeria.clases.Usuario
import com.google.gson.Gson

class PedidoActivity : PlantillaActivity(), View.OnClickListener {

    private var usuario: Usuario? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)
        val layout: ConstraintLayout = findViewById(R.id.ctrLayout4)
        if (backgroundColor != -1) {
            layout.setBackgroundColor(backgroundColor)
        } else {
            layout.background = null
        }
        usuario = intent.getSerializableExtra("usuario") as Usuario?
        val btnFavorita: Button = findViewById(R.id.btnFavorita)
        val btnPersonalizada: Button = findViewById(R.id.btnPersonalizada)
        val btnPredeterminadas: Button = findViewById(R.id.btnPredeterminadas)
        btnFavorita.setOnClickListener(this)
        btnFavorita.isEnabled = pizzaFavorita
        btnPersonalizada.setOnClickListener(this)
        btnPredeterminadas.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnFavorita -> {
                val ultimaPizzaJson: String? = this?.getSharedPreferences("Config", Context.MODE_PRIVATE)?.getString("ultimaPizza", "")
                if (ultimaPizzaJson != ""){
                    val intent = Intent(this, ConfirmacionPedidoActivity::class.java)
                    intent.putExtra("pizza", Gson().fromJson(ultimaPizzaJson, Pizza::class.java))
                    intent.putExtra("usuario", usuario)
                    startActivity(intent)
                    finish()
                } else {
                    val alert: AlertDialog.Builder = AlertDialog.Builder(this)
                    alert.setMessage("Aún no hay pizzas favoritas")
                    alert.setPositiveButton("Ok") {_, _ -> }
                    alert.create()
                    alert.show()
                }
            }
            R.id.btnPersonalizada -> {
                val intent = Intent(this, PizzasPersonalizadasActivity::class.java)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
                finish()
            }
            R.id.btnPredeterminadas -> {
                val intent = Intent(this, PizzasPredeterminadasActivity::class.java)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
                finish()
            }
        }
    }

    
}