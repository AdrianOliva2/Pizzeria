package com.example.pizzeria

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.GridLayout
import android.widget.GridView
import android.widget.RadioButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.example.pizzeria.clases.Pizza
import com.example.pizzeria.clases.Usuario
import com.example.pizzeria.enums.Tamanno

class PizzasPersonalizadasActivity : PlantillaActivity(), View.OnClickListener {

    private lateinit var layout: ConstraintLayout

    private lateinit var rdBtnFamiliar: RadioButton
    private lateinit var rdBtnMediana: RadioButton
    private lateinit var rdBtnPequenna: RadioButton

    private lateinit var chkBoxPollo: CheckBox
    private lateinit var chkBoxQueso: CheckBox
    private lateinit var chkBoxBacon: CheckBox
    private lateinit var chkBoxJamon: CheckBox

    private lateinit var chkBoxPavo: CheckBox
    private lateinit var chkBoxPeperoni: CheckBox
    private lateinit var chkBoxCebolla: CheckBox
    private lateinit var chkBoxChampinnones: CheckBox

    private lateinit var rdBtnBarbacoa: RadioButton
    private lateinit var rdBtnSpicyBBQ: RadioButton
    private lateinit var rdBtnQuesoChedar: RadioButton
    private var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizzas_personalizadas)
        layout = findViewById(R.id.ctrLayout6)
        if (backgroundColor != -1) {
            layout.setBackgroundColor(backgroundColor)
        } else {
            layout.background = null
        }
        usuario = intent.getSerializableExtra("usuario") as Usuario?

        rdBtnFamiliar = findViewById(R.id.rdBtnFamiliar)
        rdBtnMediana = findViewById(R.id.rdBtnMediana)
        rdBtnPequenna = findViewById(R.id.rdBtnPequenna)

        chkBoxPollo = findViewById(R.id.chkBoxPollo)
        chkBoxQueso = findViewById(R.id.chkBoxQueso)
        chkBoxBacon = findViewById(R.id.chkBoxBacon)
        chkBoxJamon = findViewById(R.id.chkBoxJamon)

        chkBoxPavo = findViewById(R.id.chkBoxPavo)
        chkBoxPeperoni = findViewById(R.id.chkBoxPeperoni)
        chkBoxCebolla = findViewById(R.id.chkBoxCebolla)
        chkBoxChampinnones = findViewById(R.id.chkBoxChampinnones)

        rdBtnBarbacoa = findViewById(R.id.rdBtnBarbacoa)
        rdBtnSpicyBBQ = findViewById(R.id.rdBtnSpicyBBQ)
        rdBtnQuesoChedar = findViewById(R.id.rdBtnQuesoChedar)

        val btnConfirmar: Button = findViewById(R.id.btnConfirmar)
        btnConfirmar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnConfirmar -> {
                if (rdBtnFamiliar.isChecked || rdBtnMediana.isChecked || rdBtnPequenna.isChecked) {
                    if (chkBoxPeperoni.isChecked && rdBtnQuesoChedar.isChecked) {
                        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
                        alert.setTitle("Ingredientes incompatibles")
                        alert.setMessage("No puedes seleccionar peperoni y queso chedar al mismo tiempo")
                        alert.setPositiveButton("Ok") {_, _ ->}
                        alert.create()
                        alert.show()
                    } else {
                        var ingredientes: List<String> = ArrayList()
                        val gridIngredientes: androidx.gridlayout.widget.GridLayout = findViewById(R.id.gridIngredientes)
                        for (chechBox: View in gridIngredientes.children) {
                            if (chechBox is CheckBox && chechBox.isChecked) {
                                ingredientes += chechBox.text.toString()
                                Log.i("CheckBoxIngrediente", chechBox.text.toString())
                            }
                        }
                        if (ingredientes.size < 3) {
                            val alert: AlertDialog.Builder = AlertDialog.Builder(this)
                            alert.setTitle("Ingredientes insuficientes")
                            alert.setMessage("Selecciona como mínimo 3 ingredientes")
                            alert.setPositiveButton("Ok") {_, _ ->}
                            alert.create()
                            alert.show()
                        } else {
                            var tamanno: Tamanno? = null
                            if (rdBtnFamiliar.isChecked) tamanno = Tamanno.FAMILIAR
                            else if (rdBtnMediana.isChecked) tamanno = Tamanno.MEDIANA
                            else if (rdBtnPequenna.isChecked) tamanno = Tamanno.PEQUENNA

                            val pizza = Pizza(ingredientes, tamanno)
                            var salsas: List<String> = ArrayList()

                            if (rdBtnBarbacoa.isChecked) salsas += rdBtnBarbacoa.text.toString()
                            else if (rdBtnSpicyBBQ.isChecked) salsas += rdBtnSpicyBBQ.text.toString()
                            else if (rdBtnQuesoChedar.isChecked) salsas += rdBtnQuesoChedar.text.toString()

                            pizza.salsas = salsas

                            val intent = Intent(this, ConfirmacionPedidoActivity::class.java)
                            intent.putExtra("pizza", pizza)
                            intent.putExtra("usuario", usuario)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val alert: AlertDialog.Builder = AlertDialog.Builder(this)
                    alert.setTitle("Tamaño no seleccionado")
                    alert.setMessage("Selecciona un tamaño antes de continuar")
                    alert.setPositiveButton("Ok") {_, _ ->}
                    alert.create()
                    alert.show()
                }
            }
        }
    }

}