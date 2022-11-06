package com.example.pizzeria

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.pizzeria.R

class PedidoActivity : Activity(), View.OnClickListener {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)
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

            }
            R.id.btnPersonalizada -> {

            }
            R.id.btnPredeterminadas -> {
                startActivity(Intent(this, PizzasPredeterminadasActivity::class.java))
                finish()
            }
        }
    }

    override fun onBackPressed() {}
}