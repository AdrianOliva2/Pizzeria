package com.example.pizzeria

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.pizzeria.dao.DAOUsuarios


class ConfigActivity : PlantillaActivity(), View.OnClickListener {

    lateinit var cmbBoxColor: Spinner
    lateinit var swPizzaFavorita: Switch
    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId", "MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        sharedPreferences = getSharedPreferences("Config", Context.MODE_PRIVATE)
        val layout: ConstraintLayout = findViewById(R.id.ctrLayout5)
        if (backgroundColor != -1) {
            layout.setBackgroundColor(backgroundColor)
        } else {
            layout.background = null
        }
        cmbBoxColor = findViewById(R.id.cmbBoxColor)
        val btnAplicar: Button = findViewById(R.id.btnAplicar)
        val btnVolver: Button = findViewById(R.id.btnVolver)
        val btnCerrarSesion: Button = findViewById(R.id.btnCerrarSesion)
        val btnRestablecer: Button = findViewById(R.id.btnRestablecer)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOf("Selecciona color", "Rojo", "Verde", "Azul", "Negro", "Gris", "Resetear color"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cmbBoxColor.adapter = adapter
        swPizzaFavorita = findViewById(R.id.swPizzaFavorita)
        swPizzaFavorita.isChecked = pizzaFavorita //sharedPreferences.getBoolean("pizzaPredeterminada", true)
        btnAplicar.setOnClickListener(this)
        btnVolver.setOnClickListener(this)
        btnCerrarSesion.setOnClickListener(this)
        btnRestablecer.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnAplicar -> {
                var color = -1
                when (cmbBoxColor.selectedItem as String) {
                    "Selecciona color" -> {
                        if (backgroundColor != -1) color = backgroundColor
                    }
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
                    "Resetear color" -> {
                        color = -1
                    }
                }
                with (sharedPreferences.edit()) {
                    putInt("backgroundColor", color)
                    putBoolean("pizzaPredeterminada", swPizzaFavorita.isChecked)
                    apply()
                }
                backgroundColor = color
                pizzaFavorita = swPizzaFavorita.isChecked
                startActivity(Intent(this, LoggedInActivity::class.java))
                finish()
            }

            R.id.btnCerrarSesion -> {
                if (sharedPreferences.getString("mantenerNombreUsuario", "") != "" && sharedPreferences.getString("mantenerContrasenna", "") != "") {
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.remove("mantenerNombreUsuario")
                    editor.remove("mantenerContrasenna")
                    editor.apply()
                }
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

            R.id.btnVolver -> {
                startActivity(Intent(this, LoggedInActivity::class.java))
                finish()
            }

            R.id.btnRestablecer -> {
                val alert: AlertDialog.Builder = AlertDialog.Builder(this)
                alert.setTitle("Confirmar reseteo")
                alert.setMessage("¿Estás seguro que deseas resetear la configuración?")
                alert.setPositiveButton("Si") {_, _ -> run {
                        sharedPreferences.edit().clear().apply()
                        swPizzaFavorita.isChecked = true
                        backgroundColor = -1
                        pizzaFavorita = true
                        DAOUsuarios.getInstance().resetearUsuarios()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
                alert.setNegativeButton("No") {_, _ ->}
                alert.create()
                alert.show()
            }
        }
    }

}