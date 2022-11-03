package com.example.pizzeria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class ConfigActivity : AppCompatActivity() {

    lateinit var cmbBoxColor: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        cmbBoxColor = findViewById(R.id.cmbBoxColor)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOf("Rojo", "Verde", "Azul", "Negro", "Blanco"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cmbBoxColor.adapter = adapter
    }

}