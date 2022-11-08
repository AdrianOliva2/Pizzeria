package com.example.pizzeria

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

open class PlantillaActivity : Activity() {

    protected companion object{ var backgroundColor: Int = -1 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plantilla)
    }

    fun createFromTemplate() {
        backgroundColor = getSharedPreferences("Config", Context.MODE_PRIVATE).getInt("backgroundColor", -1)
    }

    override fun onBackPressed() { }
}