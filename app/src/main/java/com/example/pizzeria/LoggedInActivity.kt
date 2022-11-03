package com.example.pizzeria

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.pizzeria.clases.Usuario

class LoggedInActivity : Activity(), View.OnClickListener {

    private var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        usuario = intent.getSerializableExtra("usuario") as Usuario?
        val btnWeb: Button = findViewById(R.id.btnWeb)
        val btnConfig: Button = findViewById(R.id.btnConfig)
        btnWeb.setOnClickListener(this)
        btnConfig.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnWeb -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.telepizza.es/")
                startActivity(intent)
            }
            R.id.btnConfig -> {
                startActivity(Intent(this, ConfigActivity::class.java))
            }
        }
    }

}