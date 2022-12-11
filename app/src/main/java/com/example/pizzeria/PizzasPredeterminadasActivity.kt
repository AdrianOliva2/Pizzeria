package com.example.pizzeria

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.pizzeria.clases.CustomAdapter
import com.example.pizzeria.clases.Pizza
import com.example.pizzeria.clases.Usuario
import com.example.pizzeria.enums.Tamanno

class PizzasPredeterminadasActivity : PlantillaActivity() {

    private lateinit var pizzas: Map<String, Pizza>
    private var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizzas_predeterminadas)
        val layout: ConstraintLayout = findViewById(R.id.ctrLayout7)
        if (backgroundColor != -1) {
            layout.setBackgroundColor(backgroundColor)
        } else {
            layout.background = null
        }
        usuario = intent.getSerializableExtra("usuario") as Usuario?
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val pizzaBBQFamiliar = Pizza(arrayListOf("Bacon", "Pollo", "Queso", "Carne de vacuno"), arrayListOf("Barbacoa"), Tamanno.FAMILIAR)
        val pizzaBBQMediana = Pizza(arrayListOf("Bacon", "Pollo", "Queso", "Carne de vacuno"), arrayListOf("Barbacoa"), Tamanno.MEDIANA)
        val pizzaBBQPequenna = Pizza(arrayListOf("Bacon", "Pollo", "Queso", "Carne de vacuno"), arrayListOf("Barbacoa"), Tamanno.PEQUENNA)
        val pizzaCuatroQuesosFamiliar = Pizza(arrayListOf("Queso mozarella", "Queso provolone", "Queso gouda", "Queso emmental"), Tamanno.FAMILIAR)
        val pizzaCuatroQuesosMediana = Pizza(arrayListOf("Queso mozarella", "Queso provolone", "Queso gouda", "Queso emmental"), Tamanno.MEDIANA)
        val pizzaCuatroQuesosPequenna = Pizza(arrayListOf("Queso mozarella", "Queso provolone", "Queso gouda", "Queso emmental"), Tamanno.PEQUENNA)
        pizzas = mapOf(Pair("BBQ Familiar", pizzaBBQFamiliar), Pair("BBQ Mediana", pizzaBBQMediana), Pair("BBQ Pequeña", pizzaBBQPequenna), Pair("Cuatro quesos Familiar", pizzaCuatroQuesosFamiliar), Pair("Cuatro quesos Mediana", pizzaCuatroQuesosMediana), Pair("Cuatro quesos Pequeña", pizzaCuatroQuesosPequenna))
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val adapter = CustomAdapter(this, pizzas, usuario)
        recyclerView.adapter = adapter
    }

    /*override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val item: String = lstPizzasPredeterminadas.getItemAtPosition(position) as String
        val pizza: Pizza? = pizzas[item]
        if (pizza != null) {
            val intent = Intent(this, ConfirmacionPedidoActivity::class.java)
            intent.putExtra("pizza", pizza)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
            finish()
        }
    }*/
    
}