package com.example.pizzeria

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pizzeria.clases.Usuario

class LoggedInActivity : PlantillaActivity(), View.OnClickListener {

    private var usuario: Usuario? = null
    private lateinit var layout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        layout = findViewById(R.id.ctrLayout3)
        if (backgroundColor != -1) {
            layout.setBackgroundColor(backgroundColor)
        } else {
            layout.background = null
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
                intent.putExtra("usuario", usuario)
                startActivity(intent)
                finish()
            }
            R.id.btnPedido -> {
                val intent = Intent(this, PedidoActivity::class.java)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
                finish()
            }
            R.id.btnConfig -> {
                val intent = Intent(this, ConfigActivity::class.java)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
                finish()
            }
        }
    }

    
}