package com.example.pizzeria

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat


class ConfigActivity : PlantillaActivity(), View.OnClickListener {

    lateinit var cmbBoxColor: Spinner

    @SuppressLint("MissingInflatedId", "MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        if (backgroundColor != -1) {
            val layout: ConstraintLayout = findViewById(R.id.ctrLayout5)
            layout.setBackgroundColor(backgroundColor)
        }
        cmbBoxColor = findViewById(R.id.cmbBoxColor)
        val btnAplicar: Button = findViewById(R.id.btnAplicar)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOf("Rojo", "Verde", "Azul", "Negro", "Gris"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cmbBoxColor.adapter = adapter
        btnAplicar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnAplicar -> {
                var color = 0
                when (cmbBoxColor.selectedItem as String) {
                    "Rojo" -> {
                        color = ContextCompat.getColor(this, android.R.color.holo_red_light)
                    }
                    "Verde" -> {
                        color = ContextCompat.getColor(this, android.R.color.holo_green_light)
                    }
                    "Azul" -> {
                        color = ContextCompat.getColor(this, android.R.color.holo_blue_light)
                    }
                    "Negro" -> {
                        color = ContextCompat.getColor(this, android.R.color.black)
                    }
                    "Gris" -> {
                        color = ContextCompat.getColor(this, android.R.color.darker_gray)
                    }
                }
                val sharedPref = this?.getSharedPreferences("Config", Context.MODE_PRIVATE) ?: return
                with (sharedPref.edit()) {
                    putInt("backgroundColor", color)
                    apply()
                }
                backgroundColor = color
                startActivity(Intent(this, LoggedInActivity::class.java))
                finish()
            }
        }
    }

}