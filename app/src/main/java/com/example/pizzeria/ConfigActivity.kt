package com.example.pizzeria

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout

class ConfigActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var cmbBoxColor: Spinner
    lateinit var layout: ConstraintLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        cmbBoxColor = findViewById(R.id.cmbBoxColor)
        layout = findViewById(R.id.ctrLayout)
        val btnAplicarColor: Button = findViewById(R.id.btnColor)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOf("Rojo", "Verde", "Azul", "Negro", "Blanco"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cmbBoxColor.adapter = adapter
        btnAplicarColor.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnColor -> {
                when (cmbBoxColor.selectedItem as String) {
                    "Rojo" -> {
                        layout.setBackgroundColor(1)
                    }
                }
            }
        }
    }

}