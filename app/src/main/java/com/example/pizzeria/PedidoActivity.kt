package com.example.pizzeria

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pizzeria.R
import com.example.pizzeria.clases.Pizza
import com.google.gson.Gson

class PedidoActivity : Activity(), View.OnClickListener {

    private lateinit var layout: ConstraintLayout
    private var intExtra: Int = -1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)
        layout = findViewById(R.id.ctrLayout3)
        intExtra = intent.getIntExtra("colorFondo", -1)
        if (intExtra != -1) {
            layout.setBackgroundColor(intExtra)
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
                val ultimaPizzaJson: String? = this?.getPreferences(Context.MODE_PRIVATE)?.getString("ultimaPizza", "")
                if (ultimaPizzaJson != ""){
                    val intent = Intent(this, ConfirmacionPedidoActivity::class.java)
                    intent.putExtra("pizza", Gson().fromJson(ultimaPizzaJson, Pizza::class.java))
                    if (intExtra != -1) intent.putExtra("colorFondo", (layout.background as ColorDrawable).color)
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

            }
            R.id.btnPredeterminadas -> {
                val intent = Intent(this, PizzasPredeterminadasActivity::class.java)
                if (intExtra != -1) intent.putExtra("colorFondo", (layout.background as ColorDrawable).color)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onBackPressed() {}
}