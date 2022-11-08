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
import com.google.gson.Gson

class PedidoActivity : PlantillaActivity(), View.OnClickListener {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)
        if (backgroundColor != -1) {
            val layout: ConstraintLayout = findViewById(R.id.ctrLayout4)
            layout.setBackgroundColor(backgroundColor)
        }
        val btnFavorita: Button = findViewById(R.id.btnFavorita)
        val btnPersonalizada: Button = findViewById(R.id.btnPersonalizada)
        val btnPredeterminadas: Button = findViewById(R.id.btnPredeterminadas)
        btnFavorita.setOnClickListener(this)
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
                startActivity(Intent(this, PizzasPersonalizadasActivity::class.java))
                finish()
            }
            R.id.btnPredeterminadas -> {
                startActivity(Intent(this, PizzasPredeterminadasActivity::class.java))
                finish()
            }
        }
    }

    
}