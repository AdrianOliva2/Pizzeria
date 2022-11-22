package com.example.pizzeria

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pizzeria.clases.Pizza
import com.example.pizzeria.clases.Usuario
import com.example.pizzeria.enums.Tamanno

class PizzasPredeterminadasActivity : PlantillaActivity(), AdapterView.OnItemClickListener {

    private lateinit var lstPizzasPredeterminadas: ListView
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
        lstPizzasPredeterminadas = findViewById(R.id.lstPizzasPredeterminadas)
        val pizzaBBQFamiliar = Pizza(arrayListOf("Bacon", "Pollo", "Queso", "Carne de vacuno"), arrayListOf("Barbacoa"), Tamanno.FAMILIAR)
        val pizzaBBQMediana = Pizza(arrayListOf("Bacon", "Pollo", "Queso", "Carne de vacuno"), arrayListOf("Barbacoa"), Tamanno.MEDIANA)
        val pizzaBBQPequenna = Pizza(arrayListOf("Bacon", "Pollo", "Queso", "Carne de vacuno"), arrayListOf("Barbacoa"), Tamanno.PEQUENNA)
        pizzas = mapOf(Pair("BBQ Familiar", pizzaBBQFamiliar), Pair("BBQ Mediana", pizzaBBQMediana), Pair("BBQ Pequeña", pizzaBBQPequenna))
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, pizzas.keys.toList())
        lstPizzasPredeterminadas.adapter = adapter
        lstPizzasPredeterminadas.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val item: String = lstPizzasPredeterminadas.getItemAtPosition(position) as String
        val pizza: Pizza? = pizzas[item]
        if (pizza != null) {
            val intent = Intent(this, ConfirmacionPedidoActivity::class.java)
            intent.putExtra("pizza", pizza)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
            finish()
        }
    }
    
}