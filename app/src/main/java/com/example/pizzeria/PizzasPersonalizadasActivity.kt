package com.example.pizzeria

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout

class PizzasPersonalizadasActivity : PlantillaActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizzas_personalizadas)
        if (backgroundColor != -1) {
            val layout: ConstraintLayout = findViewById(R.id.ctrLayout6)
            layout.setBackgroundColor(backgroundColor)
        }
    }

}