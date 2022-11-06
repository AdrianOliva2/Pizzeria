package com.example.pizzeria

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pizzeria.clases.Usuario

class LoggedInActivity : Activity(), View.OnClickListener {

    private var usuario: Usuario? = null
    private lateinit var layout: ConstraintLayout
    private var intExtra: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        layout = findViewById(R.id.ctrLayout2)
        intExtra = intent.getIntExtra("colorFondo", -1)
        if (intExtra != -1) {
            layout.setBackgroundColor(intExtra)
        }
        usuario = intent.getSerializableExtra("usuario") as Usuario?
        val btnWeb: Button = findViewById(R.id.btnWeb)
        val btnPedido: Button = findViewById(R.id.btnPedido)
        val btnConfig: Button = findViewById(R.id.btnConfig)
        btnWeb.setOnClickListener(this)
        btnPedido.setOnClickListener(this)
        btnConfig.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnWeb -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.telepizza.es/")
                startActivity(intent)
                finish()
            }
            R.id.btnPedido -> {
                val intent = Intent(this, PedidoActivity::class.java)
                if (intExtra != -1) intent.putExtra("colorFondo", (layout.background as ColorDrawable).color)
                startActivity(intent)
                finish()
            }
            R.id.btnConfig -> {
                val intent = Intent(this, ConfigActivity::class.java)
                if (intExtra != -1) intent.putExtra("colorFondo", (layout.background as ColorDrawable).color)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onBackPressed() {}
}