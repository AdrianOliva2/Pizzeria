package com.example.pizzeria

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat


class ConfigActivity : Activity(), View.OnClickListener {

    lateinit var cmbBoxColor: Spinner
    lateinit var layout: ConstraintLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        cmbBoxColor = findViewById(R.id.cmbBoxColor)
        layout = findViewById(R.id.ctrLayout)
        val btnAplicar: Button = findViewById(R.id.btnAplicar)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOf("Rojo", "Verde", "Azul", "Negro", "Blanco"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cmbBoxColor.adapter = adapter
        btnAplicar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnAplicar -> {
                when (cmbBoxColor.selectedItem as String) {

                    "Rojo" -> {
                        layout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
                    }
                    "Verde" -> {
                        layout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light))
                    }
                    "Azul" -> {
                        layout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_light))
                    }
                    "Negro" -> {
                        layout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black))
                    }
                    "Blanco" -> {
                        layout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
                    }
                }
                val intent = Intent(this, LoggedInActivity::class.java)
                intent.putExtra("colorFondo", (layout.background as ColorDrawable).color)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() { }
}